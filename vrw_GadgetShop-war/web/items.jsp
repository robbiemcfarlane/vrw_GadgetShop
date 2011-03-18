<%-- 
    Document   : items
    Created on : 17-Mar-2011, 22:43:58
    Author     : Robbie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gadgets</title>
    </head>
    <body>

        <h2>Gadgets</h2>

        <p>List of gadgets</p>
        
        <!-- List of items -->
        <%@include file="WEB-INF/includes/item_list.jsp" %>
      
    </body>
</html>
