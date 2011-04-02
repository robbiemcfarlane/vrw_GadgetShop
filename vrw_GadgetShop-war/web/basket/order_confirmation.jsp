Test
${order.id}
<c:forEach var="item" items="${order.items}">
    <p>${item.item.name}</p>
</c:forEach>