<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

</head>
<body>
<%
String baseUrl = request.getScheme() + "://" + request.getServerName() +":" + request.getLocalPort()+ request.getContextPath();
%>
	<form action="<%=baseUrl%>/login" method="post">
		<table align="center" border="1">
			<tbody>
				<tr>
					<td>User</td>
					<td><input type="text" name="user" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="" value="Enter" /></td>
				</tr>
			</tbody>

		</table>
	</form>

</body>
</html>