<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>

    
    <title>My JSP 'Typeinfo.jsp' starting page</title>
    
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
    <form action="/xm/Typeinfoctl/doupdate" >
    <table>
    <tr>
    <td>类型编号</td>
    <td>${Type.tpid}
    <input name="tpid" type="hidden" value="${Type.tpid}">
    <input name="current" type="hidden" value="${current}">
    </td>
    </tr>
    <tr>
    <td>类型名称</td>
    <td><input name="tpname" type="text" value="${Type.tpname}">
    </tr>
    <tr>
    <td>类型级别</td>
    <td>
    <label><input ${Type.tplevel==1?"checked=checked":""} name="tplevel" type="radio" value=1>1</label>
    &nbsp;&nbsp;
    <label><input ${Type.tplevel==2?"checked=checked":""} name="tplevel" type="radio" value=2>2</label>
    &nbsp;&nbsp;
    <label><input ${Type.tplevel==3?"checked=checked":""} name="tplevel" type="radio" value=3>3</label>
    </td>
    </tr>
    
    <tr>
    <td>上级类型</td>
    <td>
    <select name="tpparentid">
    <option value="0">无</option>
    <c:forEach items="${Types}" var="a">
    <option value="${a.tpid}">${a.tpname}</option>
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
