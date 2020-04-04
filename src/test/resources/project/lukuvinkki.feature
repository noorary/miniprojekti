Feature: User can add a reading tip

    Scenario: user can add a book reading tip
	   Given book form is selected
       When  title is added
       And   author is added
       And   isbn is added
       And   description is added
       And   url is added
       Then  system will respond with "succes"

	Scenario: user can add a video tip
       Given video form is selected
       When  title is added
       And   author is added
       And   description is added
       And   url is added
       Then  system will respond with "success"
