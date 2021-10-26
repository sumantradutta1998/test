package com.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.library.bean.BookBean;
import com.library.exception.BusinessException;
import com.library.service.BookService;

/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String ADD = "ADD";
	private static String EDIT = "EDIT";
	private static String DELETE = "DELETE";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String status = request.getParameter("status");
		String action = request.getParameter("action");
		try {
			if (StringUtils.equals(ADD, action)) {	
				request.setAttribute("book", new BookBean());
				request.setAttribute("action", ADD);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/bookAdd.jsp");
				rd.forward(request, response);
			} else if (StringUtils.equals(EDIT, action)) {
				BookService bookService = new BookService();
				BookBean book = bookService.getBook(bookId);
				request.setAttribute("action", EDIT);
				request.setAttribute("book", (book == null) ? new BookBean() : book);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/bookAdd.jsp");
				rd.forward(request, response);
			} else if(StringUtils.equals("DELETE", action)) {
				BookService bookService = new BookService();
				bookService.deleteBook(bookId);
				request.setAttribute("bookList", bookService.getAllBook(bookName, author, status));
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/book.jsp");
				rd.forward(request, response);
			} else {

				BookService bookService = new BookService();
				request.setAttribute("bookList", bookService.getAllBook(bookName, author, status));
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/book.jsp");
				rd.forward(request, response);

			}
		} catch (BusinessException e) {

		} catch (Exception e) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		String author = request.getParameter("author");
		String status = request.getParameter("status");
		String action = request.getParameter("action");

		BookService bookService = new BookService();

		try {
			if (StringUtils.equals(ADD, action)) {
				bookService.addBook(bookName, author, status);
				request.setAttribute("msg", "Book Successfully Added");
				request.setAttribute("action", "ADD");
				request.setAttribute("book",  new BookBean());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/bookAdd.jsp");
				rd.forward(request, response);
			} else if (StringUtils.equals(EDIT, action)) {
				bookService.saveBook(bookName, author, status, bookId);
				request.setAttribute("action", EDIT);
				BookBean book = bookService.getBook(bookId);
				request.setAttribute("book",  book);
				request.setAttribute("msg", "Updated");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/bookAdd.jsp");
				rd.forward(request, response);
			}
		} catch (BusinessException exception) {

		} catch (Exception exception) {

		}

	}

}