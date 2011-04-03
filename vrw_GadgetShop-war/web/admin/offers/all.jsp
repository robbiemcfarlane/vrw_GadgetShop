<h2>Current Offers</h2>

    
    <c:if test="${not empty offerList}">

        <ul>

            <c:forEach var="offer" items="${offerList}">
                <li>

                    <h3>Offer:</h3><a href="/admin/offer/?offer=">${offer.name}</a>
                    </br>
                    <p>
                        ${offer.description}
                    </p>
                  
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

