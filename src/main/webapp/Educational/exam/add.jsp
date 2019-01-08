<%@page isELIgnored="false" pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="../Script/Data.js" type="text/javascript"></script>
    
    <script type="text/javascript">
        $(function () {
            $("#depid").change(function () {
                var deptid = $(this).val();
                alert("hellooooo");
                if(deptid==-1){
                    alert("请选择学院");
                }else{
                    $.post(
                        "/getexamzhuanye",
                        {"examxyid":deptid},
                        function (zy) {
                            alert(zy);
                            var major = $("#majid");
                            major.html("");
                            major[0].add(new Option("---请选择---",-1));
                            for (var i = 0; i <zy.length ; i++) {
                                major[0].add(new Option(zy[i].majorname,zy[i].majorid));
                            }
                        },"json"
                    )
                }
            })
        })
    </script>

    <script type="text/javascript">
        $(function () {
            $("#majid").change(function () {
                var majorid = $(this).val();
                if(majorid==-1){
                    alert("请选择学院");
                }else{
                    $.post(
                        "/getexambj",
                        {"examzyid":majorid},
                        function (bj) {
                            alert(bj);
                            var cla = $("#claid");
                            cla.html("");
                            cla[0].add(new Option("---请选择---",-1));
                            for (var i = 0; i <bj.length ; i++) {
                                cla[0].add(new Option(bj[i].classname,bj[i].classid));
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
                <span style="float:left">当前位置是：教务中心-》考试-》新增</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="/Educational/exam/exam">【返回】</a>
                </span>
            </span>
        </div>
</div>
<div class="cztable">
	<form action="/addExam" method="post">

    <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right" width="80">编号：</td>
            <td>
				<input type="text" name="examid" />
			</td>
        </tr>

        <tr>
            <td align="right">考试编号：</td>
            <td>
				<input type="text" name="examnum" />
			</td>
        </tr>

        <tr>
            <td align="right">考试科目：</td>
            <td>
				<input type="text" name="examsubject" />
			</td>
        </tr>

		<tr>
            <td align="right">考试时间：</td>
            <td>
				<input type="text" name="examtime" />
			</td>
        </tr>

		<tr>
            <td align="right">考试班级：</td>
            <td>
				<select name="deptid" id="depid">
                    <option value="-1">---请选择---</option>
                    <c:forEach items="${examdlist}" var="ed">
                        <option value="${ed.departid}">${ed.departname}</option>
                    </c:forEach>
                </select>
                <select name="majorid" id="majid">

                </select>
                <select name="classid" id="claid">

       			</select>
			</td>
        </tr>

		<tr>
            <td align="right">考试人数：</td>
            <td>
				<input type="text" name="examcount" />
			</td>
        </tr>

		<tr>
            <td align="right">考试状态：</td>
            <td>
				<input type="text" name="examstate" />
			</td>
        </tr>
        
        <tr align="center">
            <td colspan="5" height="40">
                <div align="center">
                    <input type="submit" id="button2" value="添加"/>
                </div>
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
