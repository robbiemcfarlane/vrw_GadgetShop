<c:if test="${not empty item}">

    <h2 class="item_name">${item.name}</h2>

    <c:choose>
        <c:when test="${sessionScope.customer.VIP}">
            <p>Standard Price: <fmt:formatNumber value="${item.price}" type="currency" /></p>
            <p>VIP Price: <span style="color: red"><fmt:formatNumber value="${item.vipPrice}" type="currency" /></p>
        </c:when>
        <c:otherwise>
            <p><fmt:formatNumber value="${item.price}" type="currency" /></p>
        </c:otherwise>
    </c:choose>

    <p>${item.stockLevel} in stock</p>

    <form action="basket/add" method="post">
        <input type="hidden" name="item-id" value="${item.id}" />
        <input type="submit" name="submit" value="Add to Basket" />
    </form>

    <p class="item_long_desc">
        ${item.longDesc}
    </p>

</c:if>