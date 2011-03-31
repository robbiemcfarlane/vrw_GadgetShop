<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${not empty itemList}">
    <ul id="item_list">
        <c:forEach var="item" items="${itemList}">
            <li>
                <h3><a href="ItemController?item=${item.id}">${item.name}</a></h3>
                <p>${item.miniDesc}</p>
                <p>${item.stockLevel} in stock</p>
                <form action="basket/add" method="post">
                    <input type="hidden" name="item-id" value="${item.id}" />
                    <input type="submit" name="submit" value="Add to Basket" />
                </form>
            </li>
        </c:forEach>
    </ul>
</c:if>