<%--
    Document   : items
    Created on : 17-Mar-2011, 22:43:58
    Author     : Robbie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <!-- TODO: Change the base href so that it is dynamic (i.e., from server headers) -->
        <base href="http://localhost:8080/vrw/" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="content/style/reset.css" />
        <link rel="stylesheet" type="text/css" href="content/style/style.css" />
        <title>${param.page_title} | Global Gadgets</title>
    </head>
    <body>

        <div id="container">

            <div id="header">
                <h1>Global Gadgets</h1>

                <c:if test="${not empty sessionScope.nickname}">
                    Hi ${sessionScope.nickname}! | 
                    <form action="account/logout" method="post">
                        <input type="submit" value="Log out" class="logout_btn" />
                    </form>
                </c:if>
            </div>

            <ul id="nav" class="clearfix">
                <li><a href="./">Home</a></li>
                <li><a href="ItemController">Gadgets</a></li>
                <li><a href="account/register">Register</a></li>
                <li><a href="account/manage">Manage Account</a></li>
                <li><a href="account/login">Login</a></li>
            </ul>

            <div id="content">