Feature: Purchase the order from Ecommerce Website

  @ErrorValidation
  @Regression
  Scenario Outline: Login into user with Incorrect Password or Username
    When Logged in with username "<username>" and password "<password>"
    Then "Incorrect email or password." message is displayed
    Examples:
      | username           | password     |
      | gfdsaewq@gmail.com | Plate@1234566 |