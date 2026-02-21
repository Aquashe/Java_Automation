Feature: Purchase the order from Ecommerce Website

  @PurchaseOrder
  @Regression
  Scenario Outline: Submitting an order
    Given Logged in with username "<username>" and password "<password>"
    When i add product "<productName>" to Cart
    And Checkout "<productName>" and submit the order
    Then "Thankyou for the order." message is displayed on SuccessPage.
    Examples:
      | username           | password     | productName |
      | gfdsaewq@gmail.com | Plate@123456 | ZARA COAT 3 |
