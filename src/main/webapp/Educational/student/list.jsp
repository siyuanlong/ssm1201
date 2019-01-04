<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		confirm("确认操作？");
	}

</script>



</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span>
                <span style="float: left;">当前位置是：教务中心-》学生管理</span>
                <span style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="add.jsp">【新增学生】</a>&emsp;&emsp;&emsp;&emsp;
                </span>
            </span>
	</div>

	<div class="cztable">
		<div>
				  <form action="/Educational/student/list" method="get">
                    学生名称: 
					<input type="text" name="sname" value="${sname}" />
                     学生学号: 
					<input type="text" name="sid" value="${sid}"/>
					性别: 
					<select name="ssex">
							<option value="">--请选择--</option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					<input type="submit" value="查询" />

                </form>



		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
                        <th >学号</th>
						<th width="">姓名</th>
						<th width="">性别</th>
                        <th width="15%">联系电话</th>						
                        <th width="15%">专业</th>
						<th width="15%">登记时间</th>
						<th>操作</th>
                    </tr>

				<c:forEach items="${stupi.list}" var="stu">
                    <tr id="product1">
                        <td align="center">${stu.studentno}</td>
						<td align="center">${stu.stuname}</td>
						<td align="center">${stu.stusex}</td>
                        <td align="center">${stu.phone}</td>
						<td align="center">${stu.major.majorname}</td>
                        <td align="center"><fmt:formatDate value="${stu.regdate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
						<td align="center">
							<a href="add.jsp">修改</a>
							<a href="javascript:void(0)" onclick="del();return false" class="tablelink"> 退学</a>
							<a href="view.jsp">详细</a>
						</td> 				                    
                    </tr>
				</c:forEach>

                    <tr>
						<td colspan="20" style="text-align: center;">
							<a style="text-decoration: none;" href="/Educational/student/list?sname=${sname}&sid=${sid}&ssex=${ssex}">首页</a>
							<a style="text-decoration: none;" href="/Educational/student/list?stuindex=${stupi.pageNum-1<=1?1:stupi.pageNum-1}&sname=${sname}&sid=${sid}&ssex=${ssex}">上一页</a>

							<c:forEach begin="1" end="${stupi.pages}"  var="i">
								<a style="text-decoration: none;" href="/Educational/student/list?stuindex=${i}&sname=${sname}&sid=${sid}&ssex=${ssex}">${i}</a>
							</c:forEach>

							<a style="text-decoration: none;" href="/Educational/student/list?stuindex=${stupi.pageNum+1>=stupi.pages?stupi.pages:stupi.pageNum+1}&sname=${sname}&sid=${sid}&ssex=${ssex}">下一页</a>
							<a style="text-decoration: none;" href="/Educational/student/list?stuindex=${stupi.pages}&sname=${sname}&sid=${sid}&ssex=${ssex}">尾页</a>
							共${stupi.total}条 每页显示 ${stupi.pageNum}/${stupi.pages}
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
