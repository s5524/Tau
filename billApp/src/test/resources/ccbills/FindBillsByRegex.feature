Feature: CheckNameByRegex
        Scenario: Check name by regex
        Given There are this bill in memory
        |Id     |Name           |Price      |
        |0      |Tesco          |111        |
        |1      |Gama           |222        |
        |2      |NeoNet         |333        |
        |3      |Rogalik        |444        |
        Given Regex patern
        When Searching
        Then Bills found by pattern
        |Id     |Name           |Price      |
        |0      |Tesco          |111        |
        |2      |NeoNet         |333        |
        |3      |Rogalik        |444        |