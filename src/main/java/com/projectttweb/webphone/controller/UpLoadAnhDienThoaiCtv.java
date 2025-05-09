package com.projectttweb.webphone.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpLoadAnhDienThoaiCtv
 */
@WebServlet("/up-Load-Anh-Dien-Thoai-CTV")
public class UpLoadAnhDienThoaiCtv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpLoadAnhDienThoaiCtv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Handles HTTP POST requests to upload a phone image file from a collaborator.
	 *
	 * Processes multipart form data, saves the uploaded file to the server's images directory, sets the file name as a request attribute, and forwards the request to the `/insert-img-ctv` resource.
	 *
	 * @param request the HTTP request containing the file upload
	 * @param response the HTTP response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs during file handling
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			File file = null;
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			diskFileItemFactory.setRepository(new File("D:\\BaiHello\\MobileWebApp\\src\\main\\webapp"));
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if(!fileItem.isFormField()) {
					if(fileItem.getFieldName().equals("file")) {
					    file = new File("D:\\MobileWebApp3\\src\\main\\webapp\\imagesphone\\" + fileItem.getName());
						fileItem.write(file);
//						request.setAttribute("duongDan", file.getName());
//						RequestDispatcher rd = getServletContext().getRequestDispatcher("/insert-img");
//						rd.forward(request, response);
					}
				}
			}
			request.setAttribute("duongDan", file.getName());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/insert-img-ctv");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	

}
