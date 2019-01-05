<%--
  Created by syl
  User: Administrator
  Date: 2019/1/4
  Time: 10:55
  专业找bug
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" uri="http://java.sun.com/jsp/jstl/tag01" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>test.jsp</h4>
    <t:abc value="v" test="${1<3}">aaaaaa</t:abc>
</body>
</html>
