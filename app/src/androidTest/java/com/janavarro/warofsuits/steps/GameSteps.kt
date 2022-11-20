package com.janavarro.warofsuits.steps

import androidx.test.core.app.ActivityScenario
import com.janavarro.warofsuits.scenarios.WelcomeScenario
import com.janavarro.warofsuits.screens.GameScreen
import com.janavarro.warofsuits.screens.WelcomeScreen
import com.janavarro.warofsuits.ui.game.GameActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.cucumber.java.Before
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then

//Here you write the steps to then link them to the features with the anotations
class GameSteps : TestCase() {

    @Before("@game-feature")
    fun before() {
        ActivityScenario.launch(GameActivity::class.java)
    }

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