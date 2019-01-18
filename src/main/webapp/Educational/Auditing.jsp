<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" uri="http://java.sun.com/jsp/jstl/p" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生信息管理平台</title>
<link href="../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
<link href="../Style/ks.css" rel="stylesheet" type="text/css" />
<link href="../css/mine.css" type="text/css" rel="stylesheet">
<script src="../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../Script/jBox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="../Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="../Script/Common.js" type="text/javascript"></script>
<script src="../Script/Data.js" type="text/javascript"></script>

<script>
    /*5,10,15分页查询*/
    $(function () {
        $("#psize").change(function () {
            var size = $(this).val();
            alert(size);
            window.location.href = "/Educational/Auditing?size="+size;
        })
    })

</script>



</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》班级审核</span> <span
			style="float:right;margin-right: 8px;font-weight: bold"> 
		</span>
		</span>
	</div>

	<div class="cztable">
		<div>
			
			<ul class="seachform1">
				<form action="/Educational/Auditing" method="post">
					<li>
						<label>班级编号</label>
						<input name="cid" type="text" class="scinput1" value=""/>&nbsp;&nbsp;
                        <label>班级名称</label>
						<input name="cname" type="text" class="scinput1" value=""/>&nbsp;&nbsp;
						<input  type="submit" class="scbtn" value="查询"/>&nbsp;
					</li>
						
				</form>
			</ul>
            <br/>
            
		<table class="table_a" border="1" width="100%">
                <tbody><tr style="font-weight: bold;">
                		<td align="center">序号</td>
                        <td >院系</td>
						<td width="">班级编号</td>
						<td width="">班级名称</td>
                        <td width="15%">班主任老师</td>						
                        <td width="15%">人数</td>
						<td width="15%">班级状态</td>
						<td width="15%">操作</td>  
                    </tr>

                   
				<c:forEach items="${shenhepi.list}" var="shenhe">
					 <tr id="product1">
                     	<td align="center">${shenhe.classid}</td>
                        <td>${shenhe.department.departname}</td>
						<td>${shenhe.classnum}</td>
                        <td>${shenhe.classname}</td>
						<td>${shenhe.classteacher}</td>
                        <td>${shenhe.peoplecount}</td>
						<td>${shenhe.classstate}</td>
						<td>
							<a href="javascript:alert('操作成功！');">通过</a>&nbsp;
                            <a href="javascript:alert('操作成功！');">驳回</a>&nbsp;
							<a href="../Educational/class/view.jsp">详细</a>
						</td>
                    </tr>
				</c:forEach>
                    <tr>
                        <td colspan="20" style="text-align: center;">
							<p:page uri="/Educational/Auditing?cid=${classid}&cname=${classname}" pageInfo="${shenhepi}"/>
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
