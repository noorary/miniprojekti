Feature: User can filter reading tips with title

    Scenario: user can filter reading tips with title
        Given frontpage is opened
        When  search input is filled with title "Refactoring To Patterns"
        Then  system will respond with the right title "Refactoring To Patterns" search 