<%--
    Document   : shop_window
    Created on : 17-Mar-2011, 01:35:39
    Author     : Robbie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Window Items</title>
    </head>
    <body>

        <h2>Shop Window Items</h2>

        <!-- List of shop window items -->
        <%@include file="WEB-INF/includes/item_list.jsp" %>

    </body>
</html>
