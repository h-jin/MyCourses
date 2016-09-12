<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>payment</title>
</head>
<body>
<p>
Only MasterCard is accepted. <br>
<!--  <a href="AccountController?action=visa&user=<%=session.getAttribute("user")%>"><img src="<%=request.getContextPath() %>visa.jpg"  alt="VISA"  /></a>-->

<a href="AccountController?action=mastercard&user=<%=session.getAttribute("user")%>"><img src="/MyCourses/images/MasterCard.jpg" height="200" width="200" alt="MasterCard" /></a>
</p>

</body>
</html>