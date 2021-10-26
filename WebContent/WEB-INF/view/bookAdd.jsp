<%@page import="com.library.bean.BookBean"%>
<%@page import="java.lang.String"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
</head>
<%

BookBean book = (BookBean)request.getAttribute("book");
String action1= (String) request.getAttribute("action");

String msg = "";
if(request.getAttribute("msg") != null){
	msg = (String) request.getAttribute("msg");
			out.println(msg);
}

String status = "";
if(action1.equals("EDIT")){
	status = book.getStatus();
}


%>
<body>
<form action="" method="post">
		<table align="center" border="1">
			<tbody>
				<tr>
					<td>Book Name</td>
					<td><input type="text" name="bookName" value="<%=(book.getBookName() == null) ? "" : book.getBookName()%>"/></td>
				</tr>
				<tr>
					<td>Author</td>
					<td><input type="text" name="author" value="<%=(book.getAuthor() == null) ? "" : book.getAuthor()%>"/></td>
				</tr>
				<tr>
					<td>Status</td>
					<td>
						<select name="status">
							<option value="Active"  <%=(status.equals("Active")) ? "selected='selected'" : "" %> >Active</option>
							<option value="Inactive"  <%=(status.equals("Inactive")) ? "selected='selected'" : "" %> >Inactive</option>
							<option value="Issued" <%=(status.equals("Issued")) ? "selected='selected'" : "" %>  >Issued</option> 
						</select>
					</td>
				</tr>
				<tr rowspan="2">
					<input type="hidden" name="bookId" value="<%=(book.getBookId() == null) ? "" : book.getBookId()%>"/>
					<input type="hidden" name="action" value="<%=action1%>"/>
					<td colspan="2"><input type="submit" name="search" value="Save" /></td>
				</tr>
				
			</tbody>

		</table>
	</form>

</body>
</html>