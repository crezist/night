<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML >
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
    <form action="/xm/Typeinfoctl/doinsert" >
    <table>
    <tr>
    <td>地区名称</td>
    <td><input name="tpname" type="text">
    </tr>
    <tr>
    <td>地区级别</td>
    <td>
    <label><input name="tplevel" type="radio" value=1>国家</label>
    &nbsp;&nbsp;
    <label><input checked="checked" name="tplevel" type="radio" value=2>省/州/直辖市</label>
    &nbsp;&nbsp;
    <label><input name="tplevel" type="radio" value=3>市</label>
    </td>
    </tr>
    
    <tr>
    <td>上级地区</td>
    <td>
    <select name="tpparentid">
    <option value="0">无</option>
    <c:forEach items="${Type}" var="a">
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
