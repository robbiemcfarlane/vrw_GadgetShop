<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="page_title" value="Login" />
</jsp:include>

    <h1>Login</h1>

    <form action="login" method="post">

        <h2>Login</h2>

        <h3>Username</h3>
        <input type="text" name="nickname" maxlength="16"/>

        <h3>Password</h3>
        <input type="password" name="password" maxlength="32"/>

        <input type="submit" name="login" title="Login" value="Login" />

    </form>

<%@include file="../WEB-INF/includes/footer.jsp" %>