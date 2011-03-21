<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="pageTitle" value="Create Account" />
</jsp:include>

    <h1>Create Account</h1>

    <form action="create" method="POST">

        <h2>Personal Details</h2>

        <h3>Username</h3>
        <input type="text" name="nickname" maxlength="16"/>

        <h3>Email Address</h3>
        <input type="text" name="email" maxlength="100"/>

        <h3>Email Address Confirmation</h3>
        <input type="text" name="email-confirmation" maxlength="100"/>

        <h3>Password</h3>
        <input type="password" name="password" maxlength="32" />

        <h3>Password Confirmation</h3>
        <input type="password" name="password-confirmation" maxlength="32" />

        <h2>Contact Details</h2

        <h3>First name</h3>
        <input type="text" name="first-name" maxlength="50" />

        <h3>Last name</h3>
        <input type="text" name="last-name" maxlength="50" />

        <h3>Address 1</h3>
        <input type="text" name="address-1" maxlength="50" />

        <h3>Address 2</h3>
        <input type="text" name="address-2" maxlength="50"/>

        <h3>City</h3>
        <input type="text" name="city" maxlength="50"/>

        <h3>County</h3>
        <input type="text" name="county" maxlength="50"/>

        <h3>Postcode</h3>
        <input type="text" name="postcode" maxlength="30"/>

        <h3>Country</h3>
        <input type="text" name="country" maxlength="80"/>

        <input type="submit" name="create-account" title="Create Account" value="Create Account" />

    </form>

<%@include file="../WEB-INF/includes/footer.jsp" %>