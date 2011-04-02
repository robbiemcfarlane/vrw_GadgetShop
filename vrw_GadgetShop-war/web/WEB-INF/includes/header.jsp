<%--
    Document   : items
    Created on : 17-Mar-2011, 22:43:58
    Author     : Robbie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <!-- TODO: Change the base href so that it is dynamic (i.e., from server headers) -->
        <base href="http://localhost:8080/vrw/" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="content/style/reset.css" />
        <link rel="stylesheet" type="text/css" href="content/style/style.css" />
        <link rel="stylesheet" type="text/css" href="content/style/basket.css" />
        <title>Global Gadgets</title>
    </head>
    <body>

        <div id="container">

            <div id="header">

                <h1>Global Gadgets</h1>

                <ul id="links_tab">
                    <c:choose>
                        <c:when test="${not empty sessionScope.nickname}">
                            <li>Hi ${sessionScope.nickname}!</li>
                            <li><a href="account/logout">Log out</a></li>
                            <li><a href="account/manage">My Account</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="account/login">Log in</a></li>
                            <li><a href="account/register">Register</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li>
                        <c:choose>
                            <c:when test="${not empty shoppingBasket}">
                                Total: <fmt:formatNumber value="${shoppingBasket.total}" type="currency" />
                                (${shoppingBasket.numItems} items) |
                                <a href="basket/view">View Basket</a>
                            </c:when>
                            <c:otherwise>
                                Your basket is empty
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
                
            </div>

            <ul id="nav" class="clearfix">
                <li><a href="ExampleController">Load Test Data</a></li>
                <li><a href="./">Home</a></li>
                <li><a href="ItemController">Gadgets</a></li>
                <li><a href="Item/combo">Combo Deals</a></li>
            </ul>

            <div id="content">

                <c:if test="${not empty message}">
                    <p class="feedback_message">${message}</p>
                </c:if>