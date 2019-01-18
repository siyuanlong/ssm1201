<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../Script/Common.js" type="text/javascript"></script>
    <script src="../Script/Data.js" type="text/javascript"></script>
    <script>
	function del(){
		confirm("确认删除？");
	}

</script>
</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span>
            <span style="float:left">当前位置是：流程管理-》请假管理</span> <span style="float:right;margin-right: 8px;font-weight: bold">
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>





 <div class="cztable">
     <div><a href="/qingjia/add.jsp">添加请假单</a></div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr style="height: 25px" align="center">
                <th colspan="8">请假申请列表</th>
            </tr>

            <tr style="height: 25px" align="center">
                    <th scope="col">ID</th>
                    <th scope="col">请假人</th>
                    <th scope="col">请假天数</th>
                    <th scope="col">请假事由</th>
                    <th scope="col">请假备注</th>
                    <th scope="col">请假时间</th>
                    <th scope="col">请假状态</th>
                    <th scope="col">操作</th>
                </tr>
            <c:forEach items="${llist}" var="lb">
                <tr>
                    <td scope="col">${lb.id}</td>
                    <td scope="col">${u1.userName}</td>
                    <td scope="col">${lb.days}</td>
                    <td scope="col">${lb.content}</td>
                    <td scope="col">${lb.remark}</td>
                    <td scope="col"><fmt:formatDate value="${lb.date}" pattern="yyyy-MM-hh"></fmt:formatDate></td>
                    <td scope="col">
                        ${lb.state==0?"初始录入":lb.state==1?"开始审批":lb.state==2?"审批完成":"审核未通过"}
                    </td>
                    <td scope="col">
                        <c:if test="${lb.state==0}">
                            <a href="/qingjia/tijiao?id=${lb.id}">提交</a>
                        </c:if>
                        <c:if test="${lb.state==1||lb.state==2}">
                            <a href="#">查看</a>
                        </c:if>
                        <c:if test="${lb.state==3}">
                            <a href="#">修改</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    </div>
</body>
</html>