package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.InformationproductDAO;
import database.ProductCategoriesDAO;
import database.ProductDao;
import database.UserDao;
import model.InformationProduct;
import model.Product;
import model.User;

/**
 * Servlet implementation class AddProductCtvControl
 */
@WebServlet("/add-product-ctv")
public class AddProductCtvControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductCtvControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println("chào buổi sáng aloalo");
			HttpSession session = request.getSession(false);
			User userID = (User) session.getAttribute("khachHang");
			UserDao userDAO = new UserDao();
			User user = userDAO.selectById3(userID.getUserID());
			// --- Thông tin cơ bản ---
			String tenSanPham = request.getParameter("tenSanPham");
			String tenThuongHieu = request.getParameter("tenThuongHieu");
			String giaBan = request.getParameter("giaBan");
			String color = request.getParameter("color");
			String soLuong = request.getParameter("soLuong");
			String description = request.getParameter("description");

			// --- Thông số kỹ thuật ---
			String manHinh = request.getParameter("manHinh");
			String os = request.getParameter("os");
			String cameraSau = request.getParameter("cameraSau");
			String cameraTruoc = request.getParameter("cameraTruoc");
			String cpu = request.getParameter("cpu");
			String memory = request.getParameter("memory");
			String kichThuocManHinh = request.getParameter("kichThuocManHinh");
			String glass = request.getParameter("glass");
			String resolution = request.getParameter("resolution");
			String ram = request.getParameter("ram");
			String gpu = request.getParameter("gpu");
			String sim = request.getParameter("sim");
			String battery = request.getParameter("battery");
			String memoryCard = request.getParameter("memoryCard");

			System.out.println("=== Thông tin sản phẩm ===");
			System.out.println("Tên sản phẩm: " + tenSanPham);
			System.out.println("Thương hiệu: " + tenThuongHieu);
			System.out.println("Giá bán: " + giaBan);
			System.out.println("Màu sắc: " + color);
			System.out.println("Số lượng: " + soLuong);
			System.out.println("Mô tả: " + description);

			System.out.println("=== Thông số kỹ thuật ===");
			System.out.println("Màn hình: " + manHinh);
			System.out.println("Hệ điều hành: " + os);
			System.out.println("Camera sau: " + cameraSau);
			System.out.println("Camera trước: " + cameraTruoc);
			System.out.println("CPU: " + cpu);
			System.out.println("Bộ nhớ: " + memory);
			System.out.println("Kích thước màn hình: " + kichThuocManHinh);
			System.out.println("Glass: " + glass);
			System.out.println("Resolution: " + resolution);
			System.out.println("Ram: " + ram);
			System.out.println("GPU: " + gpu);
			System.out.println("SIM: " + sim);
			System.out.println("Battery: " + battery);
			System.out.println("Memory Card: " + memoryCard);

			String soDuHienTai = userDAO.selectSoDuHienTai(user.getUserID());
			int soTienPhaiCoc = 0;
			for (int i = 0; i < Integer.parseInt(soLuong); i++) {
				soTienPhaiCoc += 0.95 * Integer.parseInt(giaBan);
			}
			System.out.println(soTienPhaiCoc + " số tiền phải cọc");
			System.out.println(soDuHienTai + "số dư hiện tại");
			int soDuHienTaiInt = Integer.parseInt(soDuHienTai);
			String baoLoi = "";
			String msg = "";
			boolean complete = false;
			if (soDuHienTaiInt >= soTienPhaiCoc) {
				int soTienConLAi = soDuHienTaiInt - soTienPhaiCoc;
				System.out.println(soTienConLAi +" số tiền còn lại");
				if(userDAO.capNhatSoDuMoiKhiThemSP(user, String.valueOf(soTienConLAi)) > 0) {
				InformationproductDAO infoDAO = new InformationproductDAO();
				String iD = infoDAO.getInforIDCur();
				System.out.println(iD + "lalala");
				String iDNext = xuLyID2(iD);
				System.out.println(iDNext + "pôppooppp");
				ProductCategoriesDAO proCateDAO = new ProductCategoriesDAO();
				String iDCate = proCateDAO.getIDCate(tenThuongHieu);
				System.out.println(iDCate + "vvvvvv");
				ProductDao proDAO = new ProductDao();
				Date todaysDate = new Date(new java.util.Date().getTime());
				InformationProduct inforPro = new InformationProduct(iDNext, os, manHinh, glass, kichThuocManHinh,
						resolution, ram, memory, cpu, gpu, cameraSau, cameraTruoc, sim, memoryCard, battery, color);
				boolean check50 = false;
				if (infoDAO.insertInforNew(inforPro) > 0) {
					System.out.println("chào buổi trưa alo");
					String iD2 = proDAO.getInfoIDCur();
					String iD2Next = xuLyID(iD2);
					Product pro = new Product(iD2Next, tenSanPham, giaBan, giaBan, Integer.parseInt(soLuong),
							description, null, todaysDate, proCateDAO.getProCateByID(iDCate),
							infoDAO.selectByIDNew(iDNext), userDAO.selectById3(user.getUserID()), "còn hàng");
					if (proDAO.insert2(pro) > 0) {
						System.out.println("Đã ins product");
						check50 = true;
						baoLoi = "Mời bạn upload ảnh sản phẩm";
						session.setAttribute("sanPham", pro);
						request.setAttribute("page", "1");
						request.setAttribute("check50", check50);
						request.setAttribute("baoLoi", baoLoi);
					}
				}
				}
			} else {
				complete = true;
				msg = "Bạn không đủ số dư trong tài khoản hãy nạp tiền thêm";
				request.setAttribute("page", "1");
				request.setAttribute("complete", complete);
				request.setAttribute("message", msg);
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-quan-li-san-pham");
			rd.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String xuLyID(String iD) {
		// TODO Auto-generated method stub
		String ans = "";
		boolean mo = false;
		for (int i = 0; i < iD.length(); i++) {
			if (iD.charAt(i) != '0' || mo) {
				ans += iD.charAt(i);
				mo = true;
			}
		}
		int ansInt = Integer.parseInt(ans);
		int ansIntNext = ansInt + 1;
		String result = "";
		if (ansIntNext < 10) {
			result = "000" + ansIntNext;
		} else if (ansIntNext < 100) {
			result = "00" + ansIntNext;
		} else if (ansIntNext < 1000) {
			result = "0" + ansIntNext;
		} else {
			result = String.valueOf(ansIntNext);
		}
		return result;
	}

	private String xuLyID2(String iD) {
		// TODO Auto-generated method stub
		String ans = "";
		boolean mo = false;
		for (int i = 0; i < iD.length(); i++) {
			if (iD.charAt(i) != '0' || mo) {
				ans += iD.charAt(i);
				mo = true;
			}
		}
		int ansInt = Integer.parseInt(ans);
		int ansIntNext = ansInt + 1;
		String result = "";
		if (ansIntNext < 10) {
			result = "00" + ansIntNext;
		} else if (ansIntNext < 100) {
			result = "0" + ansIntNext;
		} else {
			result = String.valueOf(ansIntNext);
		}
		return result;
	}

}
