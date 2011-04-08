<h2>Checkout</h2>

<h3>Order Summary</h3>

<p>
    Number of items: ${shoppingBasket.numItems}
</p>

<p>
    Total: <fmt:formatNumber value="${shoppingBasket.total-bestOffer.saving}" type="currency" />
</p>
<p>
    Offer: ${bestOffer.name}

    <br>

    Saving of<fmt:formatNumber value=":${bestOffer.saving} />
</p>

<form action="basket/checkout" method="post">

    <fieldset>

        <legend>Payment Details</legend>

        <label for="card_type">Card Type</label>
        <select name="card-type" id="card_type">
            <option>VISA</option>
            <option>MasterCard</option>
            <option>American Express</option>
        </select>

        <label for="card_num">Card Number</label>
        <input type="text" name="card-num" />

        <label for="start_date_month">Start Date</label>
        <select name="start-date-month" id="start_date_month">
            <option>- MONTH -</option>
        </select>

        <select name="start-date-year">
            <option>- YEAR -</option>
        </select>

        <label for="end_date_month">End Date</label>
        <select name="end-date-month" id="end_date_month">
            <option>- MONTH -</option>
        </select>

        <select name="end-date-year">
            <option>- YEAR -</option>
        </select>

        <label for="cvv">CVV</label>
        <input type="text" name="cvv" />

        <label for="issue_num">Issue Number</label>
        <input type="text" name="issue-num" />

        <label for="name_on_card">Name on Card</label>
        <input type="text" name="name-on-card" />

    </fieldset>

    <fieldset>

        <legend>Delivery Options</legend>

        <label for="delivery_option">Delivery Option</label>
        <select name="delivery-options">

        </select>

    </fieldset>

    <div>
        <input type="submit" name="place-order" value="Place Order" />
    </div>

</form>