package com.janavarro.warofsuits.scenarios

import com.janavarro.warofsuits.screens.WelcomeScreen
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

//You can set up scenarios that navigate through a screen to save some code lines.
class WelcomeScenario<ScenarioData>() : BaseScenario<ScenarioData>() {

    override val steps: TestContext<ScenarioData>.() -> Unit = {

        WelcomeScreen {
            flakySafely { screenIsDisplayed() }
            flakySafely { clickPlayButton() }
        }
    }
}