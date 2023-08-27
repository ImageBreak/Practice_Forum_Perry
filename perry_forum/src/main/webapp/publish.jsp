<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %> <%@ page import="java.time.LocalDateTime" %> <%@ page
import="java.time.format.DateTimeFormatter" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
          <c:set var="isActive" value="${category.id == currentCategoryId}" />
          <li class="nav-item" role="presentation">
            <a
              class="nav-link ${isActive ? 'active' : ''}"
              href="${pageContext.request.contextPath}/topic?method=list&c_id=${category.id}"
            >
              ${category.name}
            </a>
          </li>
        </c:forEach>
        <li class="nav-item ms-auto" role="presentation">
          <img
            src="${loginUser.img}"
            class="rounded"
            width="35px"
            height="35px"
            alt=""
          />
        </li>
        <li class="nav-item" role="presentation">
          <a class="nav-link" href="#">${loginUser.username}</a>
        </li>
        <li class="nav-item" role="presentation">
          <a
            class="nav-link btn-outline-primary"
            role="tab"
            href="${pageContext.request.contextPath}/user?method=logout"
          >
            注销
          </a>
        </li>
      </ul>
    </div>

    <div class="container" style="margin-top: 100px">
      <form
        action="${pageContext.request.contextPath}/topic?method=addTopic&c_id=${currentCategoryId}"
        method="post"
        role="form"
        class="form-horizontal"
      >
        <div class="row mb-3">
          <label for="title" class="col-sm-2 col-form-label">标题</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="title" name="title" />
          </div>
        </div>
        <label for="content">内容：</label>
        <textarea
          class="form-control"
          rows="5"
          id="content"
          name="content"
        ></textarea>
        <div>你的分类是${currentCategoryId}哦</div>
        <button type="submit" class="btn btn-primary">发布</button>
        <div>
          <c:choose>
            <c:when test="${empty msg}"></c:when>
            <c:otherwise> ${msg} </c:otherwise>
          </c:choose>
        </div>
      </form>
    </div>
  </body>
</html>
