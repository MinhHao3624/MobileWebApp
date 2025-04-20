package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projectttweb.webphone.database.UserDao;
import com.projectttweb.webphone.model.User;

@WebServlet("/VerifyServlet")
public class VertifyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final long LOCK_TIME = 10000; // 10 seconds
    private Map<String, Long> lockTimes = new HashMap<>();
    private Map<String, Integer> failedAttempts = new HashMap<>(); // Track failed attempts

    public VertifyController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            String msg = "";
            boolean isXacThuc = false;
            String url = "";
            String hd = "";
            HttpSession session = request.getSession();
            String sessionId = session.getId();

            if (isLocked(sessionId)) {
                long remainingTime = getRemainingLockTime(sessionId);
                msg = "Bạn đã nhập sai. Vui lòng thử lại sau " + (remainingTime / 1000) + " giây.";
                hd = "locked";
                request.setAttribute("thongBao", msg);
                request.setAttribute("hanhDong", hd);
                request.setAttribute("remainingTime", remainingTime);
                url = "/signup-form.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                rd.forward(request, response);
                return;
            }

            if ("confirm".equals(action)) {
                hd = "confirm";
                Object obj = session.getAttribute("user");
                User user = null;

                if (obj != null) {
                    user = (User) obj;
                    String maXacNhan = request.getParameter("maXacNhan");
                    String captchaInput = request.getParameter("captcha");
                    String sessionCaptcha = (String) session.getAttribute("captcha");

                    UserDao userDao = new UserDao();
                    User us = userDao.selectById2(user);

                    if (us.getStatus() == 0) {
                        // Check if OTP is correct
                        if (!us.getAuthenticationCode().equals(maXacNhan)) {
                            // Increment failed attempts
                            int attempts = failedAttempts.getOrDefault(sessionId, 0) + 1;
                            failedAttempts.put(sessionId, attempts);

                            if (attempts >= 3) {
                                // After 3 attempts, require captcha
                                String newCaptcha = generateCaptcha();
                                session.setAttribute("captcha", newCaptcha);
                                request.setAttribute("showCaptcha", true);
                                request.setAttribute("newCaptcha", newCaptcha);
                            }

                            msg = "Mã xác nhận không chính xác. " + (attempts >= 3 ? "Vui lòng nhập cả mã captcha." : "");
                            hd = "locked";
                            request.setAttribute("showVerifyForm", true);
                        }
                        // If captcha is required (after 3 attempts) and not valid
                        else if (failedAttempts.getOrDefault(sessionId, 0) >= 3 &&
                                (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captchaInput))) {
                            msg = "Mã captcha không chính xác. Vui lòng thử lại.";
                            hd = "locked";
                            request.setAttribute("showVerifyForm", true);
                            request.setAttribute("showCaptcha", true);
                        }
                        // If verification is successful
                        else if (us.getAuthenticationCode().equals(maXacNhan) &&
                                (failedAttempts.getOrDefault(sessionId, 0) < 3 ||
                                        (failedAttempts.getOrDefault(sessionId, 0) >= 3 &&
                                                sessionCaptcha != null &&
                                                sessionCaptcha.equalsIgnoreCase(captchaInput)))) {
                            us.setStatus(1);
                            if (userDao.updateVertifyInformation2(us) > 0) {
                                msg = "Chúc mừng bạn đã xác thực tài khoản thành công";
                                isXacThuc = true;
                                // Reset failed attempts on success
                                failedAttempts.remove(sessionId);
                                lockTimes.remove(sessionId);
                            }
                        }
                    } else {
                        msg = "Bạn đã xác thực tài khoản này rồi";
                        isXacThuc = true;
                    }
                }
                url = "/signup-form.jsp";
                request.setAttribute("sourceServlet", "VertifyController");
                request.setAttribute("isXacThuc", isXacThuc);
                request.setAttribute("thongBao", msg);
                request.setAttribute("hanhDong", hd);
                request.setAttribute("remainingTime", LOCK_TIME);
                RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                // Rest of the existing code remains the same
                session = request.getSession();
                Object obj = session.getAttribute("user");
                User user = null;
                if (obj != null) {
                    user = (User) obj;
                    UserDao userDao = new UserDao();
                    User us = userDao.selectById2(user);
                    if (us.getStatus() == 0) {
                        hd = "close";
                        msg = "Bạn chưa xác nhận tài khoản. Nếu muốn hãy xác nhận qua mail";
                        request.setAttribute("hanhDong", hd);
                        request.setAttribute("thongBao", msg);
                        request.setAttribute("sourceServlet", "VertifyController");
                        url = "/signup-form.jsp";
                        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                        rd.forward(request, response);
                    } else {
                        hd = "close";
                        msg = "Bạn đã xác thực tài khoản qua email rồi!!";
                        request.setAttribute("hanhDong", hd);
                        request.setAttribute("thongBao", msg);
                        request.setAttribute("sourceServlet", "VertifyController");
                        url = "/signup-form.jsp";
                        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rest of the existing methods remain the same
    private boolean isLocked(String sessionId) {
        if (lockTimes.containsKey(sessionId)) {
            return System.currentTimeMillis() < lockTimes.get(sessionId);
        }
        return false;
    }

    private long getRemainingLockTime(String sessionId) {
        return lockTimes.get(sessionId) - System.currentTimeMillis();
    }

    private String generateCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * chars.length());
            captcha.append(chars.charAt(index));
        }
        return captcha.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}