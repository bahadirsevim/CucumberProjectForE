Feature: Test Blog Website Test
  Background:
    Given Navigate to website

  Scenario: Each news has an author
    And Verify has author

  Scenario: Each news has an image
    And Verify has image

  Scenario: Article Test
    Given Click an article
    And Verify browser title and news title is same
    And Verify the links within the news content
