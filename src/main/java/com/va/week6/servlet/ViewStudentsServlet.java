package com.va.week6.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.va.week6.dao.StudentDao;
import com.va.week6.model.Student;

/**
 * servlet for displaying stored student records
 * author: nj
 */
@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StudentDao stDao;

	public void init() {
		stDao = new StudentDao();
	}

	// gets all the stored records from the database
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// calling the select method from student dao
			List<Student> students = stDao.selectStudents();

			// sending the list to Front end file
			request.setAttribute("students", students);
			request.getRequestDispatcher("list.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute(
				"errorMessage",
				"Processing error"
			);

			request.getRequestDispatcher("list.jsp").forward(request, response);
		}
	}

}