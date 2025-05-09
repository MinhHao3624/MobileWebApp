package com.projectttweb.webphone.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projectttweb.webphone.database.UserDao;
import com.projectttweb.webphone.model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;

@WebServlet(urlPatterns = { "/LoginGoogleHandler" })
public class LoginGoogleHandler extends HttpServlet {

	/**
	 * Handles HTTP GET requests for Google OAuth login by delegating to {@code processRequest}.
	 *
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles HTTP POST requests for Google OAuth login by delegating to {@code processRequest}.
	 *
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles Google OAuth login requests by processing the authorization code, retrieving user information,
	 * managing user registration or login, and forwarding to the main data loading servlet.
	 *
	 * This method supports both GET and POST requests, sets appropriate encoding, and manages user session attributes
	 * based on whether the user already exists in the database.
	 *
	 * @param request the HTTP request containing the OAuth authorization code
	 * @param response the HTTP response for sending output or forwarding
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs during processing
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
		UserDao userDao = new UserDao();
		String url = "";
		try (PrintWriter out = response.getWriter()) {
			try {
				String code = request.getParameter("code");
				if (code == null || code.isEmpty()) {
					out.println("No code received from Google.");
					return;
				}
				String accessToken = getToken(code);
				UserGoogleDto user = getUserInfo(accessToken);
				if(userDao.kiemTraTonTai(user)) {
					HttpSession session = request.getSession();
					User user2 = userDao.selectUserByEmailGoogle(user);
					session.setAttribute("khachHang", user2);
					RequestDispatcher rd = request.getRequestDispatcher("/LoadDataMain");
					rd.forward(request, response);
				}else{
					if(userDao.insertUserGoogle(user) > 0) {
						HttpSession session = request.getSession();
						User user2 = userDao.selectUserByEmailGoogle(user);
						session.setAttribute("khachHang", user2);
						RequestDispatcher rd = request.getRequestDispatcher("/LoadDataMain");
						rd.forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Exchanges an OAuth authorization code for an access token from Google's token endpoint.
	 *
	 * @param code the authorization code received from Google's OAuth flow
	 * @return the access token string obtained from Google
	 * @throws ClientProtocolException if an HTTP protocol error occurs
	 * @throws IOException if an I/O error occurs during the request
	 */
	public static String getToken(String code) throws ClientProtocolException, IOException {
		// Gửi POST request đến Google để lấy access token
		String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form()
						.add("client_id", Constants.GOOGLE_CLIENT_ID)
						.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI)
						.add("code", code)
						.add("grant_type", Constants.GOOGLE_GRANT_TYPE)
						.build())
				.execute().returnContent().asString();

		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		return jobj.get("access_token").getAsString();
	}

	/**
	 * Retrieves Google user profile information using the provided access token.
	 *
	 * @param accessToken the OAuth 2.0 access token obtained from Google
	 * @return a UserGoogleDto object containing the user's Google profile details
	 * @throws ClientProtocolException if an HTTP protocol error occurs
	 * @throws IOException if an I/O error occurs during the request
	 */
	public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();

		return new Gson().fromJson(response, UserGoogleDto.class);
	}
}
