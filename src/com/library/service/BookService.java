package com.library.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.bean.BookBean;
import com.library.dao.BookDao;
import com.library.exception.BusinessException;

public class BookService {

	public void addBook(String bookName, String author, String status) throws BusinessException {
		BookBean bookBean = new BookBean();

		bookBean.setBookName(bookName);
		bookBean.setAuthor(author);
		bookBean.setStatus(status);
		bookBean.setCreatedBy("1");

		BookDao bookDao = new BookDao();
		bookDao.saveBook(bookBean);

	}
	public void saveBook(String bookName, String author, String status, String bookId) throws BusinessException, SQLException {
		BookBean updateData = new BookBean();
		
		updateData.setBookName(bookName);
		updateData.setAuthor(author);
		updateData.setStatus(status);
		updateData.setBookId(bookId);
		
		BookDao bookDao = new BookDao();
		bookDao.updateBook(updateData);
	}

	public ArrayList<BookBean> getAllBook(String bookName, String author, String status) throws BusinessException {
		BookBean bookBean = new BookBean();

		bookBean.setBookName(bookName);
		bookBean.setAuthor(author);
		bookBean.setStatus(status);

		BookDao bookDao = new BookDao();
		return bookDao.getBooks(bookBean);

	}

	public BookBean getBook(String bookId) throws BusinessException {
		BookBean bookBean = new BookBean();
		bookBean.setBookId(bookId);

		BookDao bookDao = new BookDao();
		List<BookBean> books = bookDao.getBooks(bookBean);

		if (books != null && books.size() > 0) {
			return books.get(0);
		}
		return null;

	}
	
	public void deleteBook(String bookId) throws BusinessException, SQLException {
		
		
		BookDao bookDao = new BookDao();
		bookDao.deleteBook(Integer.parseInt(bookId));
	}
							
}
