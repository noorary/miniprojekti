Feature: User can add tags to reading tips

    Scenario: user can add a tag to a reading tip
        Given a reading tip with title "Sunglasses", author "Glass Factory", description "Hello Glasses" and url "www.glasses.com" is added
        When  a tag with name "cool" is added to the reading tip with title "Sunglasses"
        Then  one tag with name "cool" can be found in the tag column of the reading tip with title "Sunglasses"

    Scenario: user can add more than one tags with different names to the reading tip
        Given a reading tip with title "Sky", author "Sky Smith", description "Welcome the Sky" and url "www.sky.com" is added
        When a tag with name "large" is added to the reading tip with title "Sky"
        And a tag with name "blue" is added to the reading tip with title "Sky"
        Then one tag with name "large" can be found in the tag column of the reading tip with title "Sky"
        And one tag with name "blue" can be found in the tag column of the reading tip with title "Sky"

    Scenario: user can add tags with the same name only once
        Given a reading tip with title "Cucumber", author "John Doe", description "smth" and url "www.cucumber.com" is added
        When a tag with name "fruit" is added to the reading tip with title "Cucumber"
        And a tag with name "fruit" is added to the reading tip
        Then one tag with name "fruit" can be found in the tag column of the reading tip with title "Cucumber"

    Scenario: user can add the same tag to different reading tips
        Given a reading tip with title "Apple", author "Mr. Fruit", description "delicious fruit" and url "www.apple.com" is added
        And a reading tip with title "Banana", author "Mrs. Fruit", description "nice fruit" and url "www.banana.com" is added
        When a tag with name "colorful" is added to the reading tip with title "Apple"
        And a tag with name "colorful" is added to the reading tip with title "Banana"
        Then one tag with name "colorful" can be found in the tag column of the reading tip with title "Apple"
        Then one tag with name "colorful" can be found in the tag column of the reading tip with title "Banana"


