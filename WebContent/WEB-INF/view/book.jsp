<%@page import="com.library.bean.BookBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book</title>
</head>
<%
		String baseUrl = request.getScheme() + "://" + request.getServerName() +":" + request.getLocalPort()+ request.getContextPath();
		

		List<BookBean> books = (ArrayList)request.getAttribute("bookList");
%>


<body>
	<table align="center" border="1" width="80%">
	<thead>
	<tr>
	<td colspan="4" align="center"><span style="size: 10px">Book Information</span></td>
	</tr>
		<tr align="center">	
			<td>Name</td>
			<td>Author</td>
			<td>Status</td>
			<td colspan="2">Action</td>
		</tr>
	</thead>
		<tbody>
		<%
		if(books.size() > 0){
			for(BookBean book : books){ %>
			
			<tr>
					<td><%=book.getBookName() %></td>
					<td><%=book.getAuthor() %></td>
					<td><%=book.getStatus() %></td>
					<td colspan="2"><a href="<%=baseUrl%>/book?action=EDIT&bookId=<%=book.getBookId()%>"><input type="button" value="EDIT"/></a>
					<a href="<%=baseUrl%>/book?action=DELETE&bookId=<%=book.getBookId()%>"><input type="button" value="DELETE"/></a></td>
				</tr>
			<% }
		} else { %>
			<tr align="center"> <td colspan="5" >No Data Found</td></tr> 
		<% } %>
			
						
		</tbody>

		</table>
	</form>

</body>
</html>