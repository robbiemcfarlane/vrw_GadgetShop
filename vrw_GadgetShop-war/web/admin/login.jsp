<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="page_title" value="Login" />
</jsp:include>

    <h1>Login</h1>

    <form action="admin/login" method="post">
        <fieldset>
        <legend>Please enter your login details below</legend>
            <label for="nickname">Nickname</label>
            <input type="text" name="nickname" id="nickname" maxlength="16" value="${param.nickname}" />
            <span class="error">${errorMessages.nickname}</span>

            <label for="password">Password</label>
            <input type="password" name="password" id="password" maxlength="32" value="" />
            <span class="error">${errorMessages.password}</span>

            <input type="submit" name="login" title="Login" value="Login" />
        </fieldset>
    </form>


<%@include file="../WEB-INF/includes/footer.jsp" %>