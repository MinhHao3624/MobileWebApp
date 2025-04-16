package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.projectttweb.webphone.database.ProductDao;
import com.projectttweb.webphone.model.Product;

/**
 * Servlet implementation class TaiDLSanPham
 */
@WebServlet("/tai-du-lieu-sp")
public class TaiDLSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaiDLSanPham() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			 response.setContentType("application/json;charset=UTF-8"); // Đặt Content-Type và mã hóa
		        response.setCharacterEncoding("UTF-8"); // Đặt mã hóa cho response
			String id = request.getParameter("productId");
			System.out.println(id+"aloaloalao");
			ProductDao proDAO = new ProductDao();
			Product pro = proDAO.selectProByID2(id);
			JsonResponse json = new JsonResponse(true, pro.getProductID(), pro.getName(), pro.getCategories().getNameCategories(),pro.getPrice() , pro.getPriceDis(), pro.getInformationPro().getColor(), String.valueOf(pro.getStockQuantity()), pro.getDescription(), pro.getInformationPro().getScreen(), pro.getInformationPro().getOs(), pro.getInformationPro().getCamera(), pro.getInformationPro().getCameraSelfies(), pro.getInformationPro().getCpu(), pro.getInformationPro().getMemory(), pro.getInformationPro().getScreenSize(), pro.getInformationPro().getGlass(), pro.getInformationPro().getResolution(), pro.getInformationPro().getRam(), pro.getInformationPro().getGpu(), pro.getInformationPro().getSim(), pro.getInformationPro().getBattery(), pro.getInformationPro().getMemoryCard());
			System.out.println(json.isSuccess());
			System.out.println(json.getTenSP());
			System.out.println(json.tenThuongHieu);
			System.out.println(json.giaBan);
			System.out.println(json.getGiaKM());
			System.out.println(json.getColor());
			System.out.println(json.getSoLuong());
			PrintWriter out = response.getWriter();
			
			Gson gson = new Gson();
			out.print(gson.toJson(json));
			out.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public class JsonResponse {
		public boolean success;
		public String productID;
		public String tenSP;
		public String tenThuongHieu;
		public String giaBan;
		public String giaKM;
		public String color;
		public String soLuong;
		public String des;
		// kĩ thuật
		public String manHinh;
		public String hdh;
		public String cameraSau;
		public String cameraTruoc;
		public String cpu;
		public String boNho;
		public String kichThuoc;
		public String glass;
		public String resolution;
		public String ram;
		public String gpu;
		public String sim;
		public String battery;
		public String memoryCard;
		public JsonResponse(boolean success,String proID, String tenSP, String tenThuongHieu, String giaBan, String giaKM, String color,
				String soLuong, String des, String manHinh, String hdh, String cameraSau, String cameraTruoc,
				String cpu, String boNho, String kichThuoc, String glass, String resolution, String ram, String gpu,
				String sim, String battery, String memoryCard) {
			this.success = success;
			this.tenSP = tenSP;
			this.productID = proID;
			this.tenThuongHieu = tenThuongHieu;
			this.giaBan = giaBan;
			this.giaKM = giaKM;
			this.color = color;
			this.soLuong = soLuong;
			this.des = des;
			this.manHinh = manHinh;
			this.hdh = hdh;
			this.cameraSau = cameraSau;
			this.cameraTruoc = cameraTruoc;
			this.cpu = cpu;
			this.boNho = boNho;
			this.kichThuoc = kichThuoc;
			this.glass = glass;
			this.resolution = resolution;
			this.ram = ram;
			this.gpu = gpu;
			this.sim = sim;
			this.battery = battery;
			this.memoryCard = memoryCard;
		}
		
		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}
		

		public String getProductID() {
			return productID;
		}

		public void setProductID(String productID) {
			this.productID = productID;
		}

		public String getTenSP() {
			return tenSP;
		}
		public void setTenSP(String tenSP) {
			this.tenSP = tenSP;
		}
		public String getTenThuongHieu() {
			return tenThuongHieu;
		}
		public void setTenThuongHieu(String tenThuongHieu) {
			this.tenThuongHieu = tenThuongHieu;
		}
		public String getGiaBan() {
			return giaBan;
		}
		public void setGiaBan(String giaBan) {
			this.giaBan = giaBan;
		}
		public String getGiaKM() {
			return giaKM;
		}
		public void setGiaKM(String giaKM) {
			this.giaKM = giaKM;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(String soLuong) {
			this.soLuong = soLuong;
		}
		public String getDes() {
			return des;
		}
		public void setDes(String des) {
			this.des = des;
		}
		public String getManHinh() {
			return manHinh;
		}
		public void setManHinh(String manHinh) {
			this.manHinh = manHinh;
		}
		public String getHdh() {
			return hdh;
		}
		public void setHdh(String hdh) {
			this.hdh = hdh;
		}
		public String getCameraSau() {
			return cameraSau;
		}
		public void setCameraSau(String cameraSau) {
			this.cameraSau = cameraSau;
		}
		public String getCameraTruoc() {
			return cameraTruoc;
		}
		public void setCameraTruoc(String cameraTruoc) {
			this.cameraTruoc = cameraTruoc;
		}
		public String getCpu() {
			return cpu;
		}
		public void setCpu(String cpu) {
			this.cpu = cpu;
		}
		public String getBoNho() {
			return boNho;
		}
		public void setBoNho(String boNho) {
			this.boNho = boNho;
		}
		public String getKichThuoc() {
			return kichThuoc;
		}
		public void setKichThuoc(String kichThuoc) {
			this.kichThuoc = kichThuoc;
		}
		public String getGlass() {
			return glass;
		}
		public void setGlass(String glass) {
			this.glass = glass;
		}
		public String getResolution() {
			return resolution;
		}
		public void setResolution(String resolution) {
			this.resolution = resolution;
		}
		public String getRam() {
			return ram;
		}
		public void setRam(String ram) {
			this.ram = ram;
		}
		public String getGpu() {
			return gpu;
		}
		public void setGpu(String gpu) {
			this.gpu = gpu;
		}
		public String getSim() {
			return sim;
		}
		public void setSim(String sim) {
			this.sim = sim;
		}
		public String getBattery() {
			return battery;
		}
		public void setBattery(String battery) {
			this.battery = battery;
		}
		public String getMemoryCard() {
			return memoryCard;
		}
		public void setMemoryCard(String memoryCard) {
			this.memoryCard = memoryCard;
		}
		
		
		
		
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
