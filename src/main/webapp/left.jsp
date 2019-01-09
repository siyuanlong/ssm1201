<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv=content-type content="text/html; charset=utf-8" />
        <link href="css/admin.css" type="text/css" rel="stylesheet" />
        <script language=javascript>
            function expand(e1)
            {
                childobj = document.getElementById("child"+e1);

                if (childobj.style.display == 'none')
                {
                    childobj.style.display = 'block';
                }
                else
                {
                    childobj.style.display = 'none';
                }
                return;
            }
        </script>
    </head>
    <body background=img/menu_bg.jpg >
        <table height="100%" cellspacing=0 cellpadding=0 width=170   background=./img/menu_bg.jpg border=0>
            <tr>
                <td valign=top align=middle>
                    <table cellspacing=0 cellpadding=0 width="100%" border=0>
						<tr>
                            <td height=10></td>
						</tr>
					</table>
				<c:forEach items="${u1.role.menus}" var="menu">
                    <table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a     class=menuparent onclick=expand(${menu.menuid})  href="javascript:void(0);">${menu.menuname}</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>

                    <table id="child${menu.menuid}" style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
						<c:forEach items="${menu.seconds}" var="second">
							<tr height=20>
								<td align=middle width=30>
									<img height=9 src="./img/menu_icon.gif" width=9>
								</td>
								<td>
									<%--我的资料对应user/MyUser.jsp，以及user_tb表格--%>
									<a class=menuchild href="${second.menupath}" target="right">${second.menuname}</a>
								</td>
							</tr>
						</c:forEach>
                        <tr height=4>
                            <td colspan=2></td>
						</tr>
					</table>
				</c:forEach>
				</td>
                <td width=1 bgcolor=#d1e6f7></td>
            </tr>
        </table>
    </body>
</html>