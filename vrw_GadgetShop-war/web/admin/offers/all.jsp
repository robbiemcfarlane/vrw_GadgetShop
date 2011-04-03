<h2>Current Offers</h2>

    
    <c:if test="${not empty offerList}">

        <ul>



            <c:forEach var="offer" items="${offerList}">
                <li>

                    <h3>Offer:</h3><a href="/admin/offer/?offer=">${offer.name}</a>${offer.name}
                    </br>
                    <p>
                        ${offer.description}
                    </p>
                    </br>

                    $(offer.price}

                    </br>
                    </hr>

                </li>
            </c:forEach>
        </ul>
   </c:if>

   <c:if test="${empty offerList}">
       <p>
           There are currently no offers.
       </p>
   </c:if>

