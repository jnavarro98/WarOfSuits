Feature: Game

#  Here you can write readable tests that then are linked to the code so you
#  can write tests like UATs
  @game_feature
  Scenario: Play button is displayed
    Given: User is in welcome screen
    And: User clicks play button
    Then: Game screen is displayed

  @game_feature
  Scenario: User starts game
    Given: User starts game
    Then: Game screen is displayed
