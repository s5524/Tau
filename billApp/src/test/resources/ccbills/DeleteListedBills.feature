Feature: BillDelete
        Scenario: Bill delete
        Given There are this bills in memory
        |Name           |Price      |
        |Tesco          |111        |
        |Gama           |222        |
        |NeoNet         |333        |
        |Rogalik        |444        |
        When Deleting
        |Name           |Price      |
        |Tesco          |111        |
        |Gama           |222        |
        |NeoNet         |333        |
        Then Bills that should left in db
        |Name           |Price      |
        |Rogalik        |444        |
        But Bills that should not left in db
        |Name           |Price      |
        |Tesco          |111        |
        |Gama           |222        |
        |NeoNet         |333        |