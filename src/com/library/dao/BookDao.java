package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.library.bean.BookBean;
import com.library.exception.BusinessException;

public class BookDao {

	public void saveBook(BookBean bookBean) throws BusinessException {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			String query = "INSERT INTO BOOK(id, name, author, status, created_at, created_by)"
					+ " VALUES(book_sq.nextval, ?, ?,?,SYSTIMESTAMP,?)";
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(++count, bookBean.getBookName());
			ps.setString(++count, bookBean.getAuthor());
			ps.setString(++count, bookBean.getStatus());
			ps.setString(++count, bookBean.getCreatedBy());

			int i = ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Error occurs while saving book");
		}
	}

	public ArrayList<BookBean> getBooks(BookBean bookBean) throws BusinessException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try {
			String query = "SELECT id, name, author, status, created_at, modified_at, created_by, modified_by FROM BOOK WHERE 1=1";

			if (StringUtils.isNotBlank(bookBean.getBookId())) {
				query = query + " AND id=?";
			}

			if (StringUtils.isNotBlank(bookBean.getBookName())) {
				query = query + " AND name=?";
			}

			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);

			if (StringUtils.isNotBlank(bookBean.getBookId())) {
				ps.setInt(++count, Integer.parseInt(bookBean.getBookId()));
			}

			if (StringUtils.isNotBlank(bookBean.getBookName())) {
				ps.setString(++count, bookBean.getBookName());
			}

			rs = ps.executeQuery();

			ArrayList<BookBean> books = new ArrayList<BookBean>();
			while (rs.next()) {
				BookBean book = new BookBean();

				book.setBookId(String.valueOf(rs.getInt(1)));
				book.setBookName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setStatus(rs.getString(4));

				books.add(book);
			}

			return books;

		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Error occurs while fetching book information");
		}
	}
	
	public void updateBook(BookBean updatedData) throws BusinessException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			String query="update BOOK"
					+ " SET NAME=?," + 
					" AUTHOR=?," + 
					" STATUS=?" + 
					" where ID=?";
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(++count, updatedData.getBookName());
			ps.setString(++count, updatedData.getAuthor());
			ps.setString(++count, updatedData.getStatus());
			ps.setInt(++count, Integer.parseInt(updatedData.getBookId()));
			
			int i = ps.executeUpdate();
			con.commit();
		}catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Error occured while updating the book");
		}finally {
			con.close();
		}
	}
	
	public void deleteBook(int bookId) throws BusinessException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query ="delete from BOOK where ID=?";
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, bookId);
			
			int i = ps.executeUpdate();
			con.close();
		}catch (ClassNotFoundException | SQLException e) {
			
			throw new BusinessException("Error occured while deleting the book");
		}finally {
			con.close();
		}
	}
}
