<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
    <head>
        <title>
            Perry Forum
        </title>
    </head>
    <body>
        <jsp:forward page="${pageContext.request.contextPath}/topic?method=list&c_id=1">
            
        </jsp:forward>
    </body>
</html>