<c:choose>
<c:when test="${not empty offerList}">
    <ul id="offer_list">
        <c:forEach var="offer" items="${offerList}">
            <c:if test="${offer.inStock}">
                <li>
                    <h3>${offer.name}</h3>
                    <p>${offer.description}</p>
                    <form action="basket/add" method="post">
                        <ul>
                            <c:forEach var="offerItem" items="${offer.items}">
                                <li>
                                    ${offerItem.quantity} x ${offerItem.item.name}, <fmt:formatNumber value="${offerItem.price}" type="currency" />
                                    <input type="hidden" name="item-id[]" value="${offerItem.item.id}" />
                                    <input type="hidden" name="quantity[]" value="${offerItem.quantity}" />
                                </li>
                            </c:forEach>
                        </ul>
                        <p>Normal price: <fmt:formatNumber value="${offer.totalItemPrice}" type="currency" /></p>
                        <p>Offer price: <fmt:formatNumber value="${offer.price}" type="currency" /></p>
                        <p>You save: <fmt:formatNumber value="${offer.saving}" type="currency" /></p>

                        <input type="submit" name="submit" value="Add to Basket" />
                    </form>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</c:when>
<c:otherwise>
    <p>There are no special offers available at this time.</p>
</c:otherwise>
</c:choose>