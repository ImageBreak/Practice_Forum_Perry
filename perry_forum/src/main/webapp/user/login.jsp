<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perry Forum 开发者论坛</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"
    />
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
          <c:forEach items="${categoryList}" var="category">
            <li class="nav-item" role="presentation">
              <a
                class="nav-link"
                href="${pageContext.request.contextPath}/topic?method=list&c_id=${category.id}"
              >
                ${category.name}
              </a>
            </li>
          </c:forEach>
        </ul>
    </div>
    <div class="container" style="margin-top:100px">
        <form action="${pageContext.request.contextPath}/user?method=login" method="post" role="form" class="form-horizontal">
            <div class="row mb-3">
                <label for="phone" class="col-sm-2 col-form-label">手机号</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="phone" name="phone">
                </div>
              </div>
            <div class="row mb-3">
              <label for="pwd" class="col-sm-2 col-form-label">密码</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" name="pwd">
              </div>
            </div>
            <button type="submit" class="btn btn-primary" >登录</button>
            <div class="row mb-3">
                <c:choose>
                <c:when test="${empty msg}"></c:when>
                <c:otherwise>${msg}</c:otherwise>
            </c:choose>
            </div>
        </form>
    </div>
</body>
</html>