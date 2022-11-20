package com.janavarro.warofsuits.test.steps

import com.janavarro.warofsuits.test.scenarios.WelcomeScenario
import com.janavarro.warofsuits.test.screens.GameScreen
import com.janavarro.warofsuits.test.screens.WelcomeScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then

class Steps : TestCase() {

    @Given("^User is in welcome screen")
    fun userIsOnWelcomeScreen() {
        run {
            WelcomeScreen {
                flakySafely { screenIsDisplayed() }
            }
        }
    }

    @Given("^User starts game")
    fun userStartsGame() {
        run {
            scenario(WelcomeScenario())
        }
    }

    @And("^User clicks play button")
    fun userClicksPlayButton() {
        run {
            WelcomeScreen {
                flakySafely { clickPlayButton() }
            }
        }
    }

    @Then("^Game screen is displayed")
    fun userShouldSeeGameScreen() {
        run {
            GameScreen {
                flakySafely { screenIsDisplayed() }
            }
        }
    }
}