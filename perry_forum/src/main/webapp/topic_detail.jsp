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
  
          <c:choose>
            <c:when test="${empty loginUser}">
              <li class="nav-item ms-auto" role="presentation">
                <a
                  class="nav-link btn-outline-primary"
                  role="tab"
                  href="${pageContext.request.contextPath}/user/register.jsp"
                >
                  注册
                </a>
              </li>
              <li class="nav-item" role="presentation">
                <a
                  class="nav-link btn-outline-primary"
                  role="tab"
                  href="${pageContext.request.contextPath}/user/login.jsp"
                >
                  登录
                </a>
              </li>
            </c:when>
            <c:otherwise>
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
                  href="${pageContext.request.contextPath}/reply.jsp"
                >
                  回复主题
                </a>
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
            </c:otherwise>
          </c:choose>
        </ul>

        <table
          class="table table-striped table-hover table-borderless align-middle"
        >
          <thead class="table-dark">
            <tr>
              <th class="col-1">发布人</th>
              <th class="col-2">标题</th>
              <th class="col-5">内容</th>
              <th class="col-2">发布时间</th>
              <th class="col-2">更新时间</th>
            </tr>
          </thead>
          <tbody class="table-group-divider">   
              <tr>
                <td>${topic.username}</td>
                <td>${topic.title}</td>
                <td>${topic.content}</td>
                <c:set var="cTime" value="${topic.createTime}" scope="page" />
                <c:set var="uTime" value="${topic.updateTime}" scope="page" />
                <% LocalDateTime cTime = (LocalDateTime) pageContext.getAttribute("cTime"); 
                LocalDateTime uTime = (LocalDateTime) pageContext.getAttribute("uTime"); 
                String cFormattedTime = cTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String uFormattedTime = uTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));%>
                <c:set var="cFormattedTime" value="<%= cFormattedTime %>" />
                <c:set var="uFormattedTime" value="<%= uFormattedTime %>" />
                <td>${cFormattedTime}</td>
                <td>${uFormattedTime}</td>
              </tr>
          </tbody>
        </table>
    </div>
    <div class="container">
        <table
          class="table table-striped table-hover table-borderless align-middle"
        >
          <thead class="table-light">
            <tr>
              <th class="col-1">楼层</th>
              <th class="col-2">回复人</th>
              <th class="col-7">内容</th>
              <th class="col-2">回复时间</th>
            </tr>
          </thead>
          <tbody class="table-group-divider">  
            <c:forEach items="${replyPage.list}" var="reply">
              <tr>
                <td>${reply.floor}</td>
                <td>${reply.username}</td>
                <td>${reply.content}</td>
                <c:set var="rTime" value="${reply.createTime}" scope="page" />
                <% LocalDateTime rTime = (LocalDateTime) pageContext.getAttribute("rTime"); 
                String rFormattedTime = rTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));%>
                <c:set var="rFormattedTime" value="<%= rFormattedTime %>" />
                <td>${rFormattedTime}</td>
              </tr>
            </c:forEach>
          </tbody>
          <tfoot>
            <c:if test="${replyPage.totalPage > 1}">
              <td colspan="4">
                <ul class="pagination justify-content-center">
                  <li
                    class="page-item ${replyPage.pageNumber == 1 ? 'disabled' : ''}"
                  >
                    <a
                      class="page-link"
                      href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}&page=${replyPage.pageNumber-1}"
                      >&laquo;</a
                    >
                  </li>
                  <c:forEach
                    var="currentPage"
                    begin="1"
                    end="${replyPage.totalPage}"
                  >
                    <li
                      class="page-item ${replyPage.pageNumber == currentPage ? 'active' : ''}"
                    >
                      <a
                        class="page-link"
                        href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}&page=${currentPage}"
                        >${currentPage}</a
                      >
                    </li>
                  </c:forEach>
                  <li
                    class="page-item ${replyPage.pageNumber == replyPage.totalPage ? 'disabled' : ''}"
                  >
                    <a
                      class="page-link"
                      href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}&page=${replyPage.pageNumber+1}"
                      >&raquo;</a
                    >
                  </li>
                </ul>
              </td>
            </c:if>
            <c:if test="${replyPage.totalRecord == 0}">
                <td colspan="4">
                    <div class="container text-center">
                    嘤嘤嘤，还没人回复
                    </div>
                </td>  
            </c:if>
          </tfoot>
        </table>
    </div>
</body>
</html>