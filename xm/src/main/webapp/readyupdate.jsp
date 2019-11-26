<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>

    
    <title>My JSP 'areainfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/xm/dist/css/bootstrap.min.css">
    <script type="text/javascript" src="/xm/sysjs/jquery.js"></script> 
    
    <style type="text/css">
    .container{
    width:400px;
    height:400px;
    }
    </style> 
  </head>
     
  <body>
     <div class="container" >
    <form action="/xm/areainfoctl/doupdate" >
    <table>
    <tr>
    <td>地区编号</td>
    <td>${area.aid}
    <input name="aid" type="hidden" value="${area.aid}">
    <input name="current" type="hidden" value="${current}">
    </td>
    </tr>
    <tr>
    <td>地区名称</td>
    <td><input name="aname" type="text" value="${area.aname}">
    </tr>
    <tr>
    <td>地区级别</td>
    <td>
    <label><input ${area.alevel==1?"checked=checked":""} name="alevel" type="radio" value=1>国家</label>
    &nbsp;&nbsp;
    <label><input ${area.alevel==2?"checked=checked":""} name="alevel" type="radio" value=2>省/州/直辖市</label>
    &nbsp;&nbsp;
    <label><input ${area.alevel==3?"checked=checked":""} name="alevel" type="radio" value=3>市</label>
    </td>
    </tr>
    
    <tr>
    <td>上级地区</td>
    <td>
    <select name="aparentid">
    <option value="0">无</option>
    <c:forEach items="${areas}" var="a">
    <option value="${a.aid}">${a.aname}</option>
    </c:forEach>
    </select>
    </td>
    </tr>
    </table>
    <button>提交</button>
    </form>
    </div>
  </body>
</html>
