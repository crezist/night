<%@page import="com.xm.web.xm.pojo.Areainfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML >
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
      
    
      
  </head>
  
  <body>
    <div class="container text-center">
    <label class="label label-primary">地区信息</label>
    <hr/>
    <div style="margin:auto;width:800px;height:400px">
    <table class="table table-hover text-center">
    <thead>
    <tr>
    <td>地区编号</td>
    <td>地区名称</td>
    <td>地区级别</td>
    <td>上级编号</td>
    <td>基本操作</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.areainfo}" var="a">
    <tr>
    <td>${a.aid}</td>
    <td>${a.aname}</td>
    <td>
    <c:choose>
    <c:when test="${a.alevel==1}">国家</c:when>
    <c:when test="${a.alevel==2}">省/州/自治区</c:when>
    <c:when test="${a.alevel==3}">市</c:when>
    </c:choose>
    </td>
    <td>${a.aparentid}</td>
    <td>
    <a class="btn btn-danger" href="/xm/areainfoctl/del?aid=${a.aid}&current=${current}">删除</a>
    <c:url var="update" value="/areainfoctl/readyupdate">
    <c:param name="aid" value="${a.aid}"></c:param>
    <c:param name="current" value="${current}"></c:param>
    </c:url>
    <a class="btn btn-success" href="${update}">修改</a>
    </td>
    </tr>
    </c:forEach>
    <tr>
    <td colspan="4"></td>
    <td>
    <a class="btn btn-primary" href="/xm/areainfoctl/readyinsert">添加</a>
    </td>
    </tr>
    </tbody>
    </table>
    </div>
    <div class="text-center">
    <a class="btn btn-link" href="/xm/areainfoctl/getlimitareainfo?page=1">首页</a>
    <a class="btn btn-link" href="/xm/areainfoctl/getlimitareainfo?page=${current-1<1?1:current-1}">上一页</a>
    <c:forEach begin="1" end="${pages}" var="i">
    <c:if test="${i==current}">
    <button class="btn btn-primary disable" >${i}</button>
    </c:if>
    <c:if test="${i!=current}">
     <a class="btn btn-link" href="/xm/areainfoctl/getlimitareainfo?page=${i}">${i}</a>
    </c:if>
    </c:forEach>
    <a class="btn btn-link" href="/xm/areainfoctl/getlimitareainfo?page=${current+1>pages?pages:current+1}">下一页</a>
    <a class="btn btn-link" href="/xm/areainfoctl/getlimitareainfo?page=${pages}">尾页</a>
    
    
    </div>
    </div>
  </body>
</html>
