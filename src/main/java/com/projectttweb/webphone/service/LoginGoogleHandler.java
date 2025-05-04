package com.projectttweb.webphone.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;



@WebServlet(urlPatterns = { "/LoginGoogleHandler" })
public class LoginGoogleHandler extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
		try (PrintWriter out = response.getWriter()) {
			try {
				String code = request.getParameter("code");
				if (code == null || code.isEmpty()) {
					out.println("No code received from Google.");
					return;
				}

				String accessToken = getToken(code);
				UserGoogleDto user = getUserInfo(accessToken);

				// In ra console và trình duyệt
				System.out.println(user.getName());
				System.out.println(String.format("User name: %s", user.getName()));
				System.out.println(user.getGiven_name());
				System.out.println(String.format("User name: %s", user.getGiven_name()));
				System.out.println(user.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
				out.println("<h3>Error during Google Login</h3>");
				out.println("<pre>" + e.getMessage() + "</pre>");
			}
		}
	}


	public static String getToken(String code) throws ClientProtocolException, IOException {
		// call api to get token
		String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
						.add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
		return accessToken;
	}

	public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();

		UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

		return googlePojo;
	}



}
