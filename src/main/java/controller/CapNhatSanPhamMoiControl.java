package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.InformationproductDAO;
import database.ProductCategoriesDAO;
import database.ProductDao;
import database.UserDao;
import model.InformationProduct;
import model.Product;
import model.ProductCategories;
import model.User;

/**
 * Servlet implementation class CapNhatSanPhamMoiControl
 */
@WebServlet("/capNhatSanPhamMoi")
public class CapNhatSanPhamMoiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CapNhatSanPhamMoiControl() {
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
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			// Lấy dữ liệu từ form
			HttpSession session = request.getSession(false);
			User us = (User) session.getAttribute("khachHang");
			UserDao userDAO = new UserDao();
			User user = userDAO.selectById3(us.getUserID());
			String soDuHienTai = user.getSoDu();
			int soDuHienTaiInt = Integer.parseInt(soDuHienTai);
			ProductDao proDAO = new ProductDao();
			String productID = request.getParameter("productID");
			Product proDuct = proDAO.selectProByID2(productID);
			int stockQuantity = proDuct.getStockQuantity();
			String productName = request.getParameter("productName");
			String brand = request.getParameter("brand");
			String price = request.getParameter("price");
			String discountPrice = request.getParameter("discountPrice");
			String color = request.getParameter("color");
			String quantity = request.getParameter("quantity");
			String description = request.getParameter("description");
			String screen = request.getParameter("screen");
			String os = request.getParameter("os");
			String rearCamera = request.getParameter("rearCamera");
			String frontCamera = request.getParameter("frontCamera");
			String cpu = request.getParameter("cpu");
			String memory = request.getParameter("memory");
			String screenSize = request.getParameter("screenSize");
			String glass = request.getParameter("glass");
			String resolution = request.getParameter("resolution");
			String ram = request.getParameter("ram");
			String gpu = request.getParameter("gpu");
			String sim = request.getParameter("sim");
			String battery = request.getParameter("battery");
			String memoryCard = request.getParameter("memoryCard");

			System.out.println(productName);
			System.out.println(brand);
			System.out.println(price);
			System.out.println(discountPrice);
			System.out.println(color);
			System.out.println("Quantity: " + quantity);
			System.out.println("Description: " + description);
			System.out.println("Screen: " + screen);
			System.out.println("OS: " + os);
			System.out.println("Rear Camera: " + rearCamera);
			System.out.println("Front Camera: " + frontCamera);
			System.out.println("CPU: " + cpu);
			System.out.println("Memory: " + memory);
			System.out.println("Screen Size: " + screenSize);
			System.out.println("Glass: " + glass);
			System.out.println("Resolution: " + resolution);
			System.out.println("RAM: " + ram);
			System.out.println("GPU: " + gpu);
			System.out.println("SIM: " + sim);
			System.out.println("Battery: " + battery);
			System.out.println("Memory Card: " + memoryCard);
			int quantityInt = Integer.parseInt(quantity);
			InformationproductDAO infoDAO = new InformationproductDAO();
			ProductCategoriesDAO proCateDAO = new ProductCategoriesDAO();
			if(stockQuantity == quantityInt) {
			String infoID = proDuct.getInformationPro().getInfo_ID();
			InformationProduct infoPro = infoDAO.selectByIDNew(infoID);
			ProductCategories proCate = proCateDAO.selectById(proDuct.getCategories());
			proCate.setNameCategories(brand);
			infoPro.setOs(os);
			infoPro.setScreen(screen);
			infoPro.setGlass(glass);
			infoPro.setScreenSize(screenSize);
			infoPro.setResolution(resolution);
			infoPro.setRam(ram);
			infoPro.setMemory(memory);
			infoPro.setCpu(cpu);
			infoPro.setGpu(gpu);
			infoPro.setCamera(rearCamera);
			infoPro.setCameraSelfies(frontCamera);
			infoPro.setSim(sim);
			infoPro.setMemoryCard(memoryCard);
			infoPro.setBattery(battery);
			infoPro.setColor(color);
			infoPro.setInfo_ID(infoID);

			proDuct.setName(productName);
			proDuct.setPrice(price);
			proDuct.setStockQuantity(Integer.valueOf(quantity));
			proDuct.setDescription(description);
			proDuct.setCategories(proCate);
			proDuct.setInformationPro(infoPro);

			if (infoDAO.update(infoPro) > 0) {
				if (proCateDAO.update(proCate) > 0) {
					if (proDAO.update(proDuct, productID) > 0) {
						JsonResponse json = new JsonResponse(true,false, false, productID);
						PrintWriter out = response.getWriter();
						Gson gson = new Gson();
						out.print(gson.toJson(json));
						out.flush();

					}
				}
			}
			}else if(stockQuantity > quantityInt) {
				// cộng tiền
				int soLuongBotDi = stockQuantity - quantityInt;
				int soTienCongLai = 0;
				for (int i = 0; i < soLuongBotDi; i++) {
					soTienCongLai += (int) (0.85 * Integer.parseInt(xuLyChuoi(proDuct.getPrice())));
				}
				int soDuMoi = soDuHienTaiInt + soTienCongLai;
				if(userDAO.capNhatSoDuMoiKhiThemSP(user, String.valueOf(soDuMoi)) > 0) {
					String infoID = proDuct.getInformationPro().getInfo_ID();
					InformationProduct infoPro = infoDAO.selectByIDNew(infoID);
					ProductCategories proCate = proCateDAO.selectById(proDuct.getCategories());
					proCate.setNameCategories(brand);
					infoPro.setOs(os);
					infoPro.setScreen(screen);
					infoPro.setGlass(glass);
					infoPro.setScreenSize(screenSize);
					infoPro.setResolution(resolution);
					infoPro.setRam(ram);
					infoPro.setMemory(memory);
					infoPro.setCpu(cpu);
					infoPro.setGpu(gpu);
					infoPro.setCamera(rearCamera);
					infoPro.setCameraSelfies(frontCamera);
					infoPro.setSim(sim);
					infoPro.setMemoryCard(memoryCard);
					infoPro.setBattery(battery);
					infoPro.setColor(color);
					infoPro.setInfo_ID(infoID);

					proDuct.setName(productName);
					proDuct.setPrice(price);
					proDuct.setStockQuantity(Integer.valueOf(quantity));
					proDuct.setDescription(description);
					proDuct.setCategories(proCate);
					proDuct.setInformationPro(infoPro);

					if (infoDAO.update(infoPro) > 0) {
						if (proCateDAO.update(proCate) > 0) {
							if (proDAO.update(proDuct, productID) > 0) {
								JsonResponse json = new JsonResponse(false,true, false, productID);
								PrintWriter out = response.getWriter();
								Gson gson = new Gson();
								out.print(gson.toJson(json));
								out.flush();

							}
						}
					}
				}
			}else if(stockQuantity < quantityInt) {
				// trừ tiền
				int soLuongThemVao = quantityInt - stockQuantity;
				int soTienTruThem = 0;
				for (int i = 0; i < soLuongThemVao; i++) {
					soTienTruThem +=(int) (0.95 * Integer.parseInt(xuLyChuoi(proDuct.getPrice())));
				}
				if(soDuHienTaiInt >= soTienTruThem) {
					int soDuMoi = soDuHienTaiInt - soTienTruThem;
					if(userDAO.capNhatSoDuMoiKhiThemSP(user, String.valueOf(soDuMoi)) > 0) {
						String infoID = proDuct.getInformationPro().getInfo_ID();
						InformationProduct infoPro = infoDAO.selectByIDNew(infoID);
						ProductCategories proCate = proCateDAO.selectById(proDuct.getCategories());
						proCate.setNameCategories(brand);
						infoPro.setOs(os);
						infoPro.setScreen(screen);
						infoPro.setGlass(glass);
						infoPro.setScreenSize(screenSize);
						infoPro.setResolution(resolution);
						infoPro.setRam(ram);
						infoPro.setMemory(memory);
						infoPro.setCpu(cpu);
						infoPro.setGpu(gpu);
						infoPro.setCamera(rearCamera);
						infoPro.setCameraSelfies(frontCamera);
						infoPro.setSim(sim);
						infoPro.setMemoryCard(memoryCard);
						infoPro.setBattery(battery);
						infoPro.setColor(color);
						infoPro.setInfo_ID(infoID);

						proDuct.setName(productName);
						proDuct.setPrice(price);
						proDuct.setStockQuantity(Integer.valueOf(quantity));
						proDuct.setDescription(description);
						proDuct.setCategories(proCate);
						proDuct.setInformationPro(infoPro);

						if (infoDAO.update(infoPro) > 0) {
							if (proCateDAO.update(proCate) > 0) {
								if (proDAO.update(proDuct, productID) > 0) {
									JsonResponse json = new JsonResponse(false,false, true, productID);
									PrintWriter out = response.getWriter();
									Gson gson = new Gson();
									out.print(gson.toJson(json));
									out.flush();

								}
							}
						}
					}
				}else {
					JsonResponse json = new JsonResponse(false,false, false, productID);
					PrintWriter out = response.getWriter();
					Gson gson = new Gson();
					out.print(gson.toJson(json));
					out.flush();
				}
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String xuLyChuoi(String price) {
		// TODO Auto-generated method stub
		String ans = "";
		for (int i = 0; i < price.length(); i++) {
			if(price.charAt(i) == '.') {
				continue;
			}else {
				ans += price.charAt(i);
			}
		}
		return ans;
	}

	public class JsonResponse {
		public boolean success1;
		public boolean success2;
		public boolean success3;
		public String productID;

		public JsonResponse(boolean success1, boolean success2, boolean success3, String productID) {
			this.success1 = success1;
			this.success2 = success2;
			this.success3 = success3;
			this.productID = productID;
		}

		

		public boolean isSuccess1() {
			return success1;
		}



		public void setSuccess1(boolean success1) {
			this.success1 = success1;
		}



		public boolean isSuccess2() {
			return success2;
		}



		public void setSuccess2(boolean success2) {
			this.success2 = success2;
		}



		public boolean isSuccess3() {
			return success3;
		}



		public void setSuccess3(boolean success3) {
			this.success3 = success3;
		}



		public String getProductID() {
			return productID;
		}

		public void setProductID(String productID) {
			this.productID = productID;
		}

	}

}
