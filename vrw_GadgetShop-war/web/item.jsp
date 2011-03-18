<%-- 
    Document   : item
    Created on : 18-Mar-2011, 00:03:50
    Author     : Robbie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>

        <c:if test="${not empty item}">

            <h2>${item.name}</h2>
            
            <p>
                ${item.longDesc}
            </p>

            <p>
                <fmt:formatNumber value="${item.price}" type="currency" />
            </p>

        </c:if>

    </body>

</html>
