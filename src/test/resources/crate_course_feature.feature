Feature: Create Course
  I want to be able to create a new course
  So that I can add new educational content to the platform

  Scenario: Successful course creation
    Given Course name "TDD"
    And I send the data
    Then I should see a confirmation message "TDD"
    And the new course should be listed in the course catalog
