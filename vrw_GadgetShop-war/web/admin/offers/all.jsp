<h2>Current Offers</h2>

    
    <c:if test="${not empty offerList}">

        <ul>

            <c:forEach var="offer"  items="${offerList}">
                <li>

                    <a href="/admin/offer/?offer=">${offer.name}</a>
                    
                    <p>
                        ${offer.description}
                    </p>

                    <table>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                            <th>In Shop Window</th>
                        </tr>

                    <c:forEach var="offerItem" items="${offer.items}">

            

                        <tr>
                            <td>${offerItem.name}</td>
                            <td>${offerItem.price}</td>
                            <td>${offerItem.inShopWindow}</td>
                        </tr>
                        
                    </c:forEach>
                    
                    </table>

                    Offer price:<fmt:formatNumber value="${offer.price}" type="currency" />
                    

                </li>
            </c:forEach>
        </ul>
   </c:if>

   <c:if test="${empty offerList}">
       <p>
           There are currently no offers.
       </p>
   </c:if>

