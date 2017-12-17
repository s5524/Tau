Feature: Register
        Scenario Outline: Valid user register
        Given User is at the Home Page
        And Click in the Sign in button
        And User enter random email
        And Click Create an account button
        And enter valid data <firstName> <lastName> <pas> <addres>  <state> <city> <zipCode> <phone>
        And Click on the Register button
        Then Successful LogIN message should display

    Examples:
    |firstName    |lastName         |pas        |addres|state|city|zipCode |phone      |
    |adam         |bartosz          |11111      |street|Texas|stg |55555   |111111111  |
    |bartosz      |adam             |22222      |street|Texas|gd  |44444   |222222222  |







