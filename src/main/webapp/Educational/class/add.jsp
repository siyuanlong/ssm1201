<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <%--根据学院id自动生成专业下拉框--%>
    <script type="text/javascript">
        $(function () {
            $("#xueyuan").change(function () {
                var xyid = $(this).val();
                if (xyid==-1){
                    alert("请选择学院");
                }else{
                    $.post(
                        /*
                            该处注意必须加/，不然请求会默认加上/Educational/class/
                        */
                        "/getzhuanye",
                        {"xyid":xyid},
                        function (zy) {
                            var zhuanye = $("#zhuanye");
                            zhuanye[0].length=0;
                            zhuanye[0].add(new Option("---请选择---",-1));
                            for (var i=0;i<zy.length;i++){
                                zhuanye[0].add(new Option(zy[i].majorname,zy[i].majorid));
                            }
                        },"json"
                    )
                }
            })
        })
    </script>

    <%--根据专业id自动生成班主任下拉框--%>
    <script type="text/javascript">
        $(function () {
            $("#zhuanye").change(function () {
                var zyid = $(this).val();
                if (zyid==-1){
                    alert("请选择专业");
                }else{
                    $.post(
                        /*
                            该处注意必须加/，不然请求会默认加上/Educational/class/
                        */
                        "/getct",
                        {"zyid":zyid},
                        function (ct) {
                            var banzhuren = $("#banzhuren");
                            banzhuren[0].length=0;
                            banzhuren[0].add(new Option("---请选择---",-1));
                            for (var i=0;i<ct.length;i++){
                                banzhuren[0].add(new Option(ct[i].userName,ct[i].userId+"-"+ct[i].userName));
                            }
                        },"json"
                    )
                }
            })
        })
    </script>

</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》班级管理-》新增</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/addClass" method="post">
	<table border="1" width="100%" class="table_a">
                
                <tr>
                    <td  width="120px;">班级编号：<span style="color:red">*</span>：</td>
                    <td>
                       <input type="text" name="classnum" value="201602B" />
                    </td>
                </tr>
               
               <tr>
                    <td>学院<span style="color:red">*</span>：</td>
                    <td>
                        <select id="xueyuan" name="deptid">
                            <option>---请选择---</option>
                            <c:forEach items="${dlist}" var="depart">
                                <option value="${depart.departid}">${depart.departname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>专业<span style="color:red">*</span>：</td>
                    <td>
                        <select id="zhuanye" name="majorid">

                        </select>
                    </td>
                </tr>
               
				<tr>
                    <td>班主任：<span style="color:red">*</span>：</td>
                    <td>
						<select id="banzhuren" name="teacher">

						</select>
					</td>
                </tr>
                <tr>
                    <td>班级名称：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classname" value="2016春篮球" /></td>
                </tr>
				<tr>
                    <td>人数：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="peoplecount" value="" /></td>
                </tr>
				 <tr>
                    <td>开班时间：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classbegin" value=""/></td>
                </tr>
                <tr>
                    <td>毕业时间：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classend" value="" /></td>
                </tr>
				<tr>
                    <td>QQ：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classqq" value="" /></td>
                </tr>
                <tr>
                    <td>宣传语：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="tagline" value="" /></td>
                </tr>
			
			


                <tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea name="classcontent">一个新开辟领域的探讨，探讨摸索</textarea>
                    </td>
                </tr>
				

				
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="保存">
                    </td>
                </tr>  
            </table>
	</form>
</div>

            </div>
        </div>
    </div>

</body>
</html>
