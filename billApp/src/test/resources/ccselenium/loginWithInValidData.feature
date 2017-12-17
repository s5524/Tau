Feature: InValidLogin
        Scenario Outline: InValid user login
        Given User is at the Starting Page
        And Click in the Log In button
        And User enter Invalid login data <email> <pas>
        And Click Login button
        Then allert LogIN message should be displaed

    Examples:
    |email           |pas            |
    |sz@sz.szsz      |12345ss          |
    |ssz@sz.szsz     |12345ss          |
    |szd@sz.szsz     |12345ss          |







