Feature: ValidLogin
        Scenario Outline: Valid user login
        Given User is at the Start Page
        And Click in the LogIn button
        And User enter valid login data <email> <pas>
        And Click Log in button
        Then Successful LogIN message should be displaed

    Examples:
    |email        |pas            |
    |sz@sz.sz     |12345          |







