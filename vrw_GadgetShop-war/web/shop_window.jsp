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

        <h1>Shop Window Items</h1>

        <!-- List of shop window items -->
        <c:if test="${not empty itemList}">
            <ul id="shop_window_list">
                <c:forEach var="item" items="${itemList}">
                    <li>
                        <h3><a href="ItemController?item=${item.id}">${item.name}</a></h3>
                        <p>${item.miniDesc}</p>
                        <form action="Basket" method="post">
                            <input type="hidden" name="item_id" value="${item.id}" />
                            <input type="submit" name="submit" value="Add to Basket" />
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

    </body>
</html>
