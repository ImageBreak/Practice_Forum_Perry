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
  <body p-3 m-0 border-0 bd-example bd-example-flex>
    <div class="container">
      <!-- Nav tabs -->
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <c:forEach items="${categoryList}" var="category">
          <c:set var="isActive" value="${category.id == currentCategoryId}" />
          <li class="nav-item" role="presentation">
            <a
              class="nav-link ${isActive ? 'active' : ''}"
              type="button"
              role="tab"
              id="${category.id}-tab"
              aria-controls="${category.id}-tab-pane"
              aria-selected="${isActive}"
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
                class="btn btn-outline-primary"
                role="tab"
                href="${pageContext.request.contextPath}/user/register.jsp"
              >
                注册
              </a>
            </li>
            <li class="nav-item" role="presentation">
              <a
                class="btn btn-outline-primary"
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
                class="img-fluid rounded-top"
                alt=""
              />
            </li>
            <li class="nav-item" role="presentation">${loginUser.username}</li>
            <li class="nav-item" role="presentation">
              <a
                class="nav-link"
                role="tab"
                href="${pageContext.request.contextPath}/publish.jsp"
              >
                发布主题
              </a>
            </li>
            <li class="nav-item" role="presentation">
              <a
                class="nav-link"
                role="tab"
                href="${pageContext.request.contextPath}/user?method=logout"
              >
                注销
              </a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>

      <div class="tab-content" id="myTabContent">
        <div
          class="tab-pane fade show active"
          id="${category.id}-tab-pane"
          role="tabpanel"
          aria-labelledby="${category.id}-tab"
        >
          <div class="table-responsive">
            <table
              class="table table-striped table-hover table-borderless align-middle"
              style="
                table-layout: fixed;
                word-break: break-all;
                word-wrap: break-all;
              "
            >
              <thead class="table-dark">
                <tr>
                  <th class="col-1">发布人</th>
                  <th class="col-2">标题</th>
                  <th class="col-5">内容</th>
                  <th class="col-2">发布时间</th>
                  <th class="col-2">操作</th>
                </tr>
              </thead>
              <tbody class="table-group-divider">
                <c:forEach items="${topicPage.list}" var="topic">
                  <tr>
                    <td>${topic.username}</td>
                    <td>${topic.title}</td>
                    <td>${topic.content}</td>
                    <c:set
                      var="time"
                      value="${topic.createTime}"
                      scope="page"
                    />
                    <% LocalDateTime time = (LocalDateTime) pageContext.getAttribute("time"); 
                    String formattedTime = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); %>
                    <c:set var="formattedTime" value="<%= formattedTime %>" />
                    <td>${formattedTime}</td>
                    <td><a href="${pageContext.request.contextPath}/topic?method=findDetailById&topic_id=${topic.id}">详情</td>
                  </tr>
                </c:forEach>
              </tbody>
              <tfoot>
                <c:if test="${topicPage.totalPage > 1}">
                  <td colspan="6">
                    <ul class="pagination justify-content-center">
                      <li
                        class="page-item ${topicPage.pageNumber == 1 ? 'disabled' : ''}"
                      >
                        <a
                          class="page-link"
                          href="${pageContext.request.contextPath}/topic?method=list&c_id=${currentCategoryId}&page=${topicPage.pageNumber-1}"
                          >&laquo;</a
                        >
                      </li>
                      <c:forEach var="currentPage" begin="1" end="${topicPage.totalPage}">
                        <li class="page-item ${topicPage.pageNumber == currentPage ? 'active' : ''}">
                          <a class="page-link" href="${pageContext.request.contextPath}/topic?method=list&c_id=${currentCategoryId}&page=${currentPage}">${currentPage}</a>
                        </li>
                      </c:forEach>
                      <li
                        class="page-item ${topicPage.pageNumber == topicPage.totalPage ? 'disabled' : ''}"
                      >
                        <a
                          class="page-link"
                          href="${pageContext.request.contextPath}/topic?method=list&c_id=${currentCategoryId}&page=${topicPage.pageNumber+1}"
                          >&raquo;</a
                        >
                      </li>
                    </ul>
                  </td>
                </c:if>
              </tfoot>
            </table>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
