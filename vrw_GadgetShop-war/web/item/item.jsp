<c:if test="${not empty item}">

    <h2 class="item_name">${item.name}</h2>

    <p class="item_long_desc">
        ${item.longDesc}
    </p>

    <p>
        <span class="item_price">
            <fmt:formatNumber value="${item.price}" type="currency" />
        </span>
    </p>

</c:if>