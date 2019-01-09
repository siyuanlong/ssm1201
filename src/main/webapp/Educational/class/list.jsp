<%@ page isELIgnored="false" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="p" uri="http://java.sun.com/jsp/jstl/p" %>
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
	<script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
	<script src="../../Script/Common.js" type="text/javascript"></script>
	<script src="../../Script/Data.js" type="text/javascript"></script>

	<%--处理"导出excel" 的样式--%>
	<style type="text/css">
		#sp{color: blue}
		#sp:hover{
			color: red;
			cursor: pointer;
		}
	</style>

	<%--处理全选--%>
	<script type="text/javascript" >
		$(function () {
			//这里使用click点击事件
			$("#all").click(function () {
				var c = $(this)[0].checked;
				var cids = $("[name=cids]");
                for (var i = 0; i < cids.length ; i++) {
					cids[i].checked = c;
                }
            })

			//ajax提交表单
            $("#sp").click(function(){
                    $("#form02").submit();
            })
        })
	</script>
</head>
<body>

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》班级管理</span> <span
				style="float: right; margin-right: 8px; font-weight: lighter">
        <%--<a style="text-decoration: blink" href="javascript:alert('操作成功！');">【导出excel】&nbsp;&nbsp;</a>--%>
			<span style="text-decoration: blink" id="sp">【导出excel】&nbsp;&nbsp;</span>
            <a style="text-decoration: blink" href="/Educational/class/getdeparts">【新增班级】&emsp;&emsp;&emsp;&emsp;</a>
		</span>
		</span>
</div>

<div class="cztable">
	<div>

		<ul class="seachform1">
			<form action="/Educational/class/list" method="post">
				<li>
					<label>班级名称:</label>
					<input name="cname" type="text" class="scinput1" value="${classname}"/>&nbsp;&nbsp;
					<label>院系名称:</label>
					<input name="dname" type="text" class="scinput1" value="${deptname}"/>&nbsp;&nbsp;
					<input  type="submit" class="scbtn" value="查询"/>&nbsp;
				</li>
			</form>

		<form id="form02" action="/daochu" method="post">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
				<tr style="font-weight: bold;">
					<th  width="8%">
						<input type="checkbox" id="all"/>
					</th>
					<th >院系</th>
					<th width="">班级编号</th>
					<th width="">班级名称</th>
					<th width="15%">班主任老师</th>
					<th width="15%">人数</th>
					<th width="15%">班级状态</th>
					<th width="20%">操作</th>
				</tr>
				<c:forEach items="${pi.list}" var="cla">
					<tr id="product1">
						<td  width="8%" align="center">
							<input type="checkbox" value="${cla.deptid}" name="cids"/>
						</td>
						<td align="center">${cla.department.departname}</td>
						<td align="center">${cla.classnum}</td>
						<td align="center">${cla.classname}</td>
						<td align="center">${cla.classteacher}</td>
						<td align="center">${cla.peoplecount}</td>
						<td align="center">${cla.classstate}</td>
						<td align="center">
							<a href="info.jsp">详情</a>
							<a href="javascript:location.href='../book/list.jsp'">发书</a>

						</td>
					</tr>
				</c:forEach>


                    <tr>
                        <td colspan="20" style="text-align: center;">						
						<%--<a style="text-decoration: none;" href="/Educational/class/list?cname=${classname}&dname=${deptname}">首页</a>
						<a style="text-decoration: none;" href="/Educational/class/list?index=${pi.pageNum-1<=1?1:pi.pageNum-1}&cname=${classname}&dname=${deptname}">上一页</a>

						<c:forEach begin="1" end="${pi.pages}"  var="i">
							<a style="text-decoration: none;" href="/Educational/class/list?index=${i}&cname=${classname}&dname=${deptname}">${i}</a>
						</c:forEach>

						<a style="text-decoration: none;" href="/Educational/class/list?index=${pi.pageNum+1>=pi.pages?pi.pages:pi.pageNum+1}&cname=${classname}&dname=${deptname}">下一页</a>
						<a style="text-decoration: none;" href="/Educational/class/list?index=${pi.pages}&cname=${classname}&dname=${deptname}">尾页</a>
					    共${pi.total}条 每页显示 ${pi.pageNum}/${pi.pages}--%>
						<p:page uri="/Educational/class/list?cname=${classname}&dname=${deptname}" pageInfo="${pi}"></p:page>

                        </td>
                    </tr>
                </tbody>
			</table>
		</form>

	</div>

	</div>
	</div>

	</div>
</body>
</html>
