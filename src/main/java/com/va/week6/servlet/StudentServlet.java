package com.va.week6.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.va.week6.dao.StudentDao;
import com.va.week6.model.Student;

/**
 * servlet implementation
 * author: nj
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private StudentDao stDao;

	public void init() {
		stDao = new StudentDao();
	}

	// handles submission
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// getting all the values from the form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");
		String mobileNumber = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String captcha = request.getParameter("captcha");

		// using a static captcha for now
		// this needs to match the value shown in the image
		String correctCaptcha = "W68HP";

		// checking if any required field is blank
		if (username == null || username.trim().equals("") ||
			password == null || password.trim().equals("") ||
			retypePassword == null || retypePassword.trim().equals("") ||
			mobileNumber == null || mobileNumber.trim().equals("") ||
			email == null || email.trim().equals("") ||
			captcha == null || captcha.trim().equals("")) {

			request.setAttribute("errorMessage", "All required fields must be completed.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// checking if both passwords match
		if (!password.equals(retypePassword)) {
			request.setAttribute("errorMessage", "Passwords do not match.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// checking if the phone number contains exactly 10 digits
		if (!mobileNumber.matches("[0-9]{10}")) {
			request.setAttribute("errorMessage", "Mobile number must contain exactly 10 digits.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// checking if the email format is valid
		if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
			request.setAttribute("errorMessage", "Please enter a valid email address.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// checking if the entered captcha matches the image
		if (!captcha.equalsIgnoreCase(correctCaptcha)) {
			request.setAttribute("errorMessage", "The captcha entered is incorrect.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		String hashedPassword = "";

		try {
			// hashing the password before storing it
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			StringBuilder hashValue = new StringBuilder();

			for (byte b : hashBytes) {
				hashValue.append(String.format("%02x", b));
			}

			hashedPassword = hashValue.toString();

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
				"errorMessage",
				"Processing error"
			);

			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		// adding the values that need to be stored into the model
		Student st = new Student();
		st.setUsername(username);
		st.setPassword(hashedPassword);
		st.setMobileNumber(mobileNumber);
		st.setEmail(email);

		try {
			int result = stDao.registerStudent(st);

			// only redirect after the database insert works
			if (result > 0) {
				response.sendRedirect("success.jsp");
			} else {
				request.setAttribute(
					"errorMessage",
					"Registration unsuccessful. Please try again."
				);

				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
				"errorMessage",
				"Registration unsuccessful. Please try again."
			);

			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
