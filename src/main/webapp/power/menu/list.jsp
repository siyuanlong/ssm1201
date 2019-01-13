<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/p" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>
        学生信息管理平台
    </title>
    <link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css"/>
    <link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css"/>
    <link href="../../Style/ks.css" rel="stylesheet" type="text/css"/>
    <link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>
    <style type="text/css">
        .sp {
            color: blue;
        }

        .sp:hover {
            color: red;
            cursor: pointer;
        }
    </style>

    <script>
        $(function () {
            /*全选不选*/
            $("#all").click(function () {
                var menuids = $("[name=mids]");
                for (var i = 0; i <menuids.length ; i++) {
                    menuids[i].checked=$(this)[0].checked;
                }
            })
            /*导出excel表格*/
            $("#sp01").click(function () {
                $("#form01")[0].action="/power/menu/export";
                $("#form01").submit();
            })
            /*批量删除*/
            $("#sp02").click(function () {
                $("#form01")[0].action="/power/menu/deleteall";
                $("#form01").submit();
            })

        })
    </script>
</head>
<body>

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》菜单管理</span> <span
                style="float:right;margin-right: 8px;font-weight: bold">
			<span style="text-decoration: none;" id="sp01" class="sp">【导出excel】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="text-decoration: none;" id="sp02" class="sp">【批量删除】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" href="/power/menu/add">【新增菜单】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
</div>
<div class="morebt">

</div>

<div class="cztable">

    <form id="form01" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr style="height: 25px" align="center">
                <th><input type="checkbox" id="all"/></th>
                <th scope="col">
                    序号
                </th>

                <th scope="col">
                    菜单名称
                </th>
                <th scope="col">
                    UTL
                </th>
                <th scope="col">
                    状态
                </th>
                <th scope="col">
                    操作
                </th>
            </tr

            <c:forEach items="${menupi.list}" var="menu" varStatus="c">
                <tr>
                    <th align="center"><input type="checkbox" value="${menu.menuid}" name="mids"/></th>
                    <td align="center">
                        ${c.count+(menupi.pageNum-1)*5}
                    </td>
                    <td align="center">
                        ${menu.menuname}
                    </td>
                    <td align="center">
                        ${menu.menupath}
                    </td>

                    <td align="center">
                        ${menu.menustate}
                    </td>

                    <td align="center">
                        <a href="/power/menu/info?mid=${menu.menuid}">详情</a>
                        <a href="/power/menu/geteditinfo?mid=${menu.menuid}">修改</a>
                        <a href="javascript:void(0)" onclick="del();return false" class="tablelink"> 删除</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="20" style="text-align: center;">
                    <p:page uri="/power/menu/getmenus" pageInfo="${menupi}"></p:page>
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