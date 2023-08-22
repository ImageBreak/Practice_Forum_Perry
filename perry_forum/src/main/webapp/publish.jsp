<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
import="java.time.LocalDateTime" %> <%@ page
import="java.time.format.DateTimeFormatter" %>

<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Perry Forum 开发者论坛</title>
  </head>
  <body>
    <c:set var="topic" scope="page" value="${LocalDateTime.now()}" />

    <p>Original LocalDateTime: <c:out value="${topic}" /></p>

    <% LocalDateTime now = LocalDateTime.now(); 
       String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); %>
    <c:set var="formattedTime" value="<%= formattedTime %>" />
    <p>Formatted LocalDateTime: <c:out value="${formattedTime}" /></p>
  </body>
</html>
