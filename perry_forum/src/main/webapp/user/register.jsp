<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %> <%@ page import="java.time.LocalDateTime" %> <%@ page
import="java.time.format.DateTimeFormatter" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

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
        <form action="${pageContext.request.contextPath}/user?method=register" method="post" role="form" class="form-horizontal">
            <div class="row mb-3">
              <label for="phone" class="col-sm-2 col-form-label">手机号</label>
              <div class="col-sm-10">
                <input type="tel" class="form-control" id="phone" name="phone">
              </div>
            </div>
            <div class="row mb-3">
                <label for="username" class="col-sm-2 col-form-label">用户名</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="username" name="username">
                </div>
              </div>
            <div class="row mb-3">
              <label for="pwd" class="col-sm-2 col-form-label">密码</label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" name="pwd">
              </div>
            </div>
            <fieldset class="row mb-3">
              <legend class="col-form-label col-sm-2 pt-0">性别</legend>
              <div class="col-sm-10">
                <div class="form-check">
                  <input class="form-check-input" type="radio" name="sex" id="gridRadios1" value="1">
                  <label class="form-check-label" for="gridRadios1">
                    男
                  </label>
                </div>
                <div class="form-check">
                  <input class="form-check-input" type="radio" name="sex" id="gridRadios2" value="0">
                  <label class="form-check-label" for="gridRadios2">
                    女
                  </label>
                </div>
              </div>
            </fieldset>
            <button type="submit" class="btn btn-primary" >注册</button>

    </div>
</body>
</html>