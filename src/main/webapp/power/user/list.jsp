<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>

    <style type="text/css">
        .sp{
            color: blue;
        }
        .sp:hover{
            color: red;
            cursor: pointer;
        }
    </style>

    <script type="text/javascript">
        //批量删除
		$(function () {

            $("#sp1").click(function () {
                /*
                方式一:
                注意:第一个$("#form01")一定要加[0]，否则没有效果
                */
                $("#form01")[0].action = "/power/user/excel";
                $("#form01").submit();

                /*方式二:
                document.forms[0].action = "/power/user/excel";
                document.forms[0].submit();
                */
            })

            $("#sp2").click(function () {
                $("#form01")[0].action = "/power/user/deleteUser";
                $("#form01").submit();
                /*document.forms[0].action = "/power/user/deleteUser";
                document.forms[0].submit();*/
            })
            /*
                设置多选全选和全不选
                注意使用$("#id")[0].checked的方式可以得到该标签对应的value值
                注意ids是数组，赋值前必须先进行遍历
           */
            $("#all").click(function () {
               var ids = $("[name=ids]");
                for (var i = 0; i <ids.length ; i++) {
                    ids[i].checked = $(this)[0].checked;
                }
            })
        })
	</script>

</head>
<body>


<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》人员管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
            <span style="text-decoration: none;" class="sp" id="sp1">【导出excel】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="text-decoration: none;" class="sp" id="sp2">【批量删除】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" href="/power/user/allrole" >【新增人员】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>
 <div class="cztable" style="width: 100%;">
     <form id="form01" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px;" align="center">
                    
                    <th  width="8%">
						<input type="checkbox" id="all"/>
					</th>
					<th scope="col" width="15%">
                        序号
                    </th>
                    <th scope="col" width="15%">
                        账号
                    </th>
                    <th scope="col" width="15%">
                        姓名
                    </th>
                    <th scope="col" width="15%">
                        角色
                    </th>
                   
                    <th scope="col" >
                        操作
                    </th>
                </tr>

            <c:forEach items="${userspi.list}" var="users" varStatus="u">
                <tr align="center">
					<th><input type="checkbox" value="${users.userId}" name="ids"/></th>
                    <td>
                        ${c.count+(userspi.pageNum-1)*5}
                    </td>
                    
                    <td>
                      ${users.userName}
                    </td>
                    <td>
                       <a href="info.jsp">${users.userRealname}</a>
                    </td>
                    
                    <td>&nbsp;
                        ${users.role.rolename}
                    </td>
                    
                    <td>&nbsp;
                        <a href="/selectOne?udid=${users.userId}">修改</a>
						<a href="/deleteOne?udid=${users.userId}"> 删除</a>
                    </td>
                </tr>

            </c:forEach>

                <tr>
                    <td colspan="20" style="text-align: center;">
                        <p:page uri="/power/user/list" pageInfo="${userspi}"></p:page>
                    </td>
                </tr>

            </tbody>
        </table>
     </form>
    </div>
    </div>
</body>

</html>