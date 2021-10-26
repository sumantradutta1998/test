<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<%
String baseUrl = request.getScheme() + "://" + request.getServerName() +":" + request.getLocalPort()+ request.getContextPath();
%>
		<table align="center" border="1">
			<tbody>
				<tr>
					
					<td><a href="<%=baseUrl%>/member"><input type="button" name="addMember" value="Add Member"></a></td>
					<td><a href="<%=baseUrl%>/book?action=ADD"><input type="button" name="addBook" value="Add Book"></a></td>
				</tr>
			</tbody>
		</table>
<form action="" method="post">
		<table align="center" border="1">
			<tbody>
				
				<tr rowspan="2">
					
					<td><input type="text" name="book" /></td>
					<td colspan="2"><input type="submit" name="search" value="Search" /></td>
				</tr>
				
			</tbody>

		</table>
	</form>

</body>
</html>