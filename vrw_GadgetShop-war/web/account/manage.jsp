<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="page_title" value="Login" />
</jsp:include>


    <h1>Account Management</h1>

    <form action="account/manage" method="post">

        <fieldset>
        <legend>Personal Details</legend>

            <label for="nickname">Nickname</label>
            <input type="text" name="nickname" id="nickname" maxlength="16" value="${customer.nickname}" />
            <span class="error">${errorMessages.nickname}</span>

            <label for="email">Email Address</label>
            <input type="text" name="email" id="email" maxlength="100" value="${customer.email}" />
            <span class="error">${errorMessages.email}</span>         

            <label for="password">Password</label>
            <input type="password" name="password" id="password" maxlength="32" value="${customer.password}" />
            <span class="error">${errorMessages.password}</span>

           

        </fieldset>

        <fieldset>
        <legend>Contact Details</legend>

            <label for="first-name">First name</label>
            <input type="text" name="first-name" id="first-name" maxlength="50" value="${customer['firstName']}" />
            <span class="error">${errorMessages['first-name']}</span>

            <label for="last-name">Last name</label>
            <input type="text" name="last-name" id="last-name" maxlength="50" value="${customer['lastName']}" />
            <span class="error">${errorMessages['last-name']}</span>

            <label for="address-1">Address 1</label>
            <input type="text" name="address-1" id="address-1" maxlength="50" value="${customer['address1']}" />
            <span class="error">${errorMessages['address-1']}</span>

            <label for="address-2">Address 2</label>
            <input type="text" name="address-2" id="address-2" maxlength="50" value="${customer['address2']}" />
            <span class="error">${errorMessages['address-2']}</span>

            <label for="city">City</label>
            <input type="text" name="city" id="city" maxlength="50" value="${customer.city}" />
            <span class="error">${errorMessages.city}</span>

            <label for="county">County</label>
            <input type="text" name="county" id="county" maxlength="50" value="${customer.county}" />
            <span class="error">${errorMessages.county}</span>

            <label for="postcode">Postcode</label>
            <input type="text" name="postcode" id="postcode" maxlength="30" value="${customer.postcode}" />
            <span class="error">${errorMessages.postcode}</span>

            <label for="country">Country</label>
            <input type="text" name="country" id="country" maxlength="80" value="${customer.country}" />
            <span class="error">${errorMessages.country}</span>

        </fieldset>

        <div><input type="submit" name="manage-account" title="Update Account Details" value="Update Account Details" /></div>

    </form>


<%@include file="../WEB-INF/includes/footer.jsp" %>