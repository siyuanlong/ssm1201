<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${s}" var="stu">
                <tr>
                    <td>${stu.sid}</td>
                    <td>${stu.sname}</td>
                    <td>${stu.sage}</td>
                    <td>${stu.ssex}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
