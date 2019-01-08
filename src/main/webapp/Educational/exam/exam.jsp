<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生信息管理平台</title>
<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
<link href="../../css/mine.css" type="text/css" rel="stylesheet">
<script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../../Script/jBox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="../../Script/Common.js" type="text/javascript"></script>
<script src="../../Script/Data.js" type="text/javascript"></script>

<script>
	function del(){
		confirm("确认删除？");
	}

</script>



</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》考试</span> <span
			style="float:right;margin-right: 8px;font-weight: bold"> <a
				style="text-decoration: none" href="/Educational/exam/getdeparts">【新增考试】</a>
		</span>
		</span>
	</div>

	<div class="cztable">
		<div>
			
			<ul class="seachform1">
				<form action="/Educational/exam/exam" method="post">
					<li>
						<label>考试科目</label>
						<input name="subject" type="text" class="scinput1" value="${subject}"/>&nbsp;&nbsp;
						<input  type="submit" class="scbtn" value="查询"/>&nbsp;
					</li>
						
				</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
					<th scope="col">编号</th>
					<th scope="col">考试编号</th>
					<th scope="col">考试科目</th>
					<th scope="col">考试时间</th>
					<th scope="col">考试班级</th>
					<th scope="col">考试状态</th>
					<th scope="col">操作</th>
				</tr>

			<c:forEach items="${exampi.list}" var="exam">
				<tr align="center">
					<td>${exam.examid}</td>
					<td>${exam.examnum}</td>
					<td>${exam.examsubject}</td>
					<td><fmt:formatDate value="${exam.examtime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
					<td>${exam.classes.classname}</td>
					<td>${exam.examstate}</td>
					<td>
						<a href="view.jsp">详细</a>
						<a href="list.jsp">考试成绩</a>
                        <a href="reAdd.jsp">组织补考</a>
					</td>
				</tr>
			</c:forEach>

				 <tr>
                        <td colspan="20" style="text-align: center;">						
							<p:page uri="/Educational/exam/exam?subject=${subject}" pageInfo="${exampi}"/>
                        </td>
                    </tr>
			</tbody>
		</table>
	</div>

	</div>
	</div>

	</div>
</body>
</html>
