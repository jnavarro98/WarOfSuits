package com.janavarro.warofsuits.steps

import com.janavarro.warofsuits.screens.GameScreen
import com.janavarro.warofsuits.screens.WelcomeScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then

class WelcomeSteps : TestCase() {

    @Given("^User is in welcome screen")
    fun userIsOnWelcomeScreen() {
        run {
            WelcomeScreen {
                flakySafely { screenIsDisplayed() }
            }
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

    @Then("^User should game screen")
    fun userShouldSeeGameScreen() {
        run {
            GameScreen {
                flakySafely { screenIsDisplayed() }
            }
        }
    }
}