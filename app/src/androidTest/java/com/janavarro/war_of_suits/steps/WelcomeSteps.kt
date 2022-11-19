package com.janavarro.war_of_suits.steps

import com.janavarro.war_of_suits.screens.WelcomeScreen
import com.janavarro.war_of_suits.ui.welcome.WelcomeActivity
import com.janavarro.war_of_suits.util.lazyActivityScenarioRule
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.junit.Rule

class WelcomeSteps : BaseTestCase() {

    @get:Rule
    val activityRule = lazyActivityScenarioRule<WelcomeActivity>()

    @Before("@war_of_suits.feature")
    override fun before() {
        activityRule.launch()
        super.before()
    }

    @After("@war_of_suits.feature")
    override fun after() {
        activityRule.launch()
        super.before()
    }

    @Given("User is on welcome screen")
    fun userIsOnWelcomeScreen() {
        run {
            WelcomeScreen {
                flakySafely { screenIsDisplayed() }
            }
        }
    }

    @Then("User should see play button")
    fun userShouldSeePlayButton() {
        run {
            WelcomeScreen {
                flakySafely { playButtonIsDisplayed() }
            }
        }
    }
}