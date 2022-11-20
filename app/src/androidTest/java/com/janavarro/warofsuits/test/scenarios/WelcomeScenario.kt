package com.janavarro.warofsuits.test.scenarios

import com.janavarro.warofsuits.test.screens.WelcomeScreen
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class WelcomeScenario<ScenarioData>() : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {

        WelcomeScreen {
            flakySafely { screenIsDisplayed() }
            flakySafely { clickPlayButton() }
        }
    }
}