<h2>Gadgets</h2>

<p>List of gadgets</p>

<!-- List of items -->
<c:if test="${not empty itemList}">
    <form action="admin/items" method="post">
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Stock Level</th>
                <th>Price</th>
                <th>In Shop Window</th>
            </tr>
            <c:forEach var="item" items="${itemList}">
                <tr>
                    <td><a href="admin/item/${item.id}">${item.name}</a></td>
                    <td>${item.miniDesc}</td>
                    <td>${item.stockLevel}</td>
                    <td><fmt:formatNumber value="${item.price}" type="currency" /></td>
                    <td><input type="checkbox" name="in-shop-window" value="true" <c:if test="${item.inShopWindow}">checked="checked"</c:if> /></td>
                </tr>
            </c:forEach>
        </table>

        <input type="submit" name="update" value="Update Changes" />

    </form>
</c:if>