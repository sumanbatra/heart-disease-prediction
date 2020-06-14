package com.ec.project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/predict")
public class EJBStatelessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private EJBStatelessLocal lrs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			out.println("KNN Predict");
			out.println("<br>");
			String age = request.getParameter("age");
			String sex = request.getParameter("sex");
			String chestPain = request.getParameter("chestPain");
			String trestbps = request.getParameter("trestbps");
			String cholestrol = request.getParameter("cholestrol");
			String fbs = request.getParameter("fbs");
			String restecg = request.getParameter("restecg");
			String thalach = request.getParameter("thalach");
			String exang = request.getParameter("exang");
			String slope = request.getParameter("slope");
			String ca = request.getParameter("ca");
			String oldpeak = request.getParameter("oldpeak");
			String thal = request.getParameter("thal");

			out.println("Predict result: " + lrs.predict(59, 1, 0, 110, 239, 0, 0, 142, 1, 1.2, 1, 1, 3));
			out.println("<br>");

		} catch (Exception ex) {
			throw new ServletException(ex);
		} finally {
			out.close();
		}
	}
}
