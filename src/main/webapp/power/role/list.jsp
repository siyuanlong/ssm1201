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

    <script>
        /*5,10,15分页查询*/
        $(function () {
            $("#psize").change(function () {
                var size = $(this).val();
                alert(size);
                window.location.href = "/power/role/list?size="+size;
            })
        })
    </script>

</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》角色管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
			<a style="text-decoration: none;" href="/power/role/getMenuInfo">【新增角色】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>





 <div class="cztable">
     <form method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px" align="center">
                	<th><input type="checkbox"/></th>
                    <th scope="col">
                        序号
                    </th>
                    
                    <th scope="col">
                        角色名称
                    </th>
                    <th scope="col">
                        状态
                    </th>
                    <th scope="col" width="300px">
                        操作
                    </th>
                </tr>

            <c:forEach items="${rolepi.list}" var="r" varStatus="c">
                <tr align="center">
                    <th><input type="checkbox"/></th>
                    <td>
                        ${c.count+(rolepi.pageNum-1)*5}
                    </td>
                    <td>
                        ${r.rolename}
                    </td>                    
                    <td>&nbsp;
                        ${r.rolestate==1?"启用":"禁用"}
                    </td>
                    
                    <td>&nbsp;
                    	<a href="/power/role/changeState?state=${r.rolestate}&rid=${r.roleid}">${r.rolestate==1?"禁用":"启用"}</a>
                        <a href="/power/role/info?rid=${r.roleid}">详情</a>
                        <a href="/power/role/getInfo?rid=${r.roleid}">修改</a>
						<a href="/power/role/deleterole?rid=${r.roleid}">删除</a>
                    </td>
                </tr>
            </c:forEach>

                <tr>
                    <td colspan="20" style="text-align: center;">
                        <p:page uri="/power/role/list" pageInfo="${rolepi}"></p:page>
                    </td>
                </tr>
            </tbody>
        </table>
     </form>
        </div>
    </div>

    </div>
</body>
</html>