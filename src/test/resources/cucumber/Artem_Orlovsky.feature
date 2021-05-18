Feature: website Epam test
  Scenario: Language change
    Given I have entered site epam.com
    When I switch location to Ukraine
    Then I should be redirected to https://careers.epam.ua/
  Scenario: Language change (India)
    Given I have entered site epam.com
    When I switch location to India (English)
    Then I should be redirected to https://welcome.epam.in/
  Scenario: Accessing FAQ
    Given I have entered site epam.com
    When I click on FAQ option
    Then I should be redirected to page with FAQ
  Scenario: Privacy policy redirect
    Given I have entered site epam.com
    And I press on contact us button
    When I press on link to read Privacy policy
    Then I should be redirected to Privacy policy site
  Scenario: Accessing search bar:
    Given I have entered site epam.com
    And I switch location to Ukraine
    When I click on magnifier symbol
    Then I should see search area
  Scenario: Going to training portal
    Given I have entered site epam.com
    And I switch location to Ukraine
    And I go to education info for students
    When I click on link in the first step to become junior
    Then I should be redirected to https://training.epam.ua/
  Scenario: Phone call
    Given I have entered site epam.com
    And I switch location to Ukraine
    When I go to company's contacts
    Then I should be able to call by click on phone number
  Scenario: Ask for mail
    Given I have entered site epam.com
    And I switch location to Ukraine
    When I go to company's contacts
    Then I should be able to mail to email by click