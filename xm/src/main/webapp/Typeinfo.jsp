<%@page import="com.xm.web.xm.pojo.Typeinfo"%>
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
      
      
  </head>
  
  <body>
    <div class="container text-center">
    <label class="label label-primary">类型信息</label>
    <hr/>
    <div style="margin:auto;width:800px;height:400px">
    <table class="table table-hover text-center">
    <thead>
    <tr>
    <td>类型编号</td>
    <td>类型名称</td>
    <td>类型级别</td>
    <td>上级编号</td>
    <td>基本操作</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.Typeinfo}" var="a">
    <tr>
    <td>${a.tpid}</td>
    <td>${a.tpname}</td>
    <td>
    <c:choose>
    <c:when test="${a.tplevel==1}">1</c:when>
    <c:when test="${a.tplevel==2}">2</c:when>
    <c:when test="${a.tplevel==3}">3</c:when>
    </c:choose>
    </td>
    <td>${a.tpparentid}</td>
    <td>
    <a class="btn btn-danger" href="/xm/Typeinfoctl/del?tpid=${a.tpid}&current=${current}">删除</a>
    <c:url var="update" value="/Typeinfoctl/readyupdate">
    <c:param name="tpid" value="${a.tpid}"></c:param>
    <c:param name="current" value="${current}"></c:param>
    </c:url>
    <a class="btn btn-success" href="${update}">修改</a>
    </td>
    </tr>
    </c:forEach>
    <tr>
    <td colspan="4"></td>
    <td>
    <a class="btn btn-primary" href="/xm/Typeinfoctl/readyinsert">添加</a>
    </td>
    </tr>
    </tbody>
    </table>
    </div>
    <div class="text-center">
    <a class="btn btn-link" href="/xm/Typeinfoctl/getlimitTypeinfo?page=1">首页</a>
    <a class="btn btn-link" href="/xm/Typeinfoctl/getlimitTypeinfo?page=${current-1<1?1:current-1}">上一页</a>
    <c:forEach begin="1" end="${pages}" var="i">
    <c:if test="${i==current}">
    <button class="btn btn-primary disable" >${i}</button>
    </c:if>
    <c:if test="${i!=current}">
     <a class="btn btn-link" href="/xm/Typeinfoctl/getlimitTypeinfo?page=${i}">${i}</a>
    </c:if>
    </c:forEach>
    <a class="btn btn-link" href="/xm/Typeinfoctl/getlimitTypeinfo?page=${current+1>pages?pages:current+1}">下一页</a>
    <a class="btn btn-link" href="/xm/Typeinfoctl/getlimitTypeinfo?page=${pages}">尾页</a>
    
    
    </div>
    </div>
  </body>
</html>
