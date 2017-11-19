Feature: BillDelete
        Scenario: Bill delete
        Given There are this bills in memory
        |Id     |Name           |Price      |
        |0      |Tesco          |111        |
        |1      |Gama           |222        |
        |2      |NeoNet         |333        |
        |3      |Rogalik        |444        |
        When Deleting
        |Id     |Name           |Price      |
        |0      |Tesco          |111        |
        |1      |Gama           |222        |
        |2      |NeoNet         |333        |
        Then Bills that should left in db
        |Id     |Name           |Price      |
        |0      |Rogalik        |444        |
        But Bills that should not left in db
        |Id     |Name           |Price      |
        |0      |Tesco          |111        |
        |1      |Gama           |222        |
        |2      |NeoNet         |333        |
