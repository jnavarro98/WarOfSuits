package com.janavarro.war_of_suits.runner

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions

@CucumberOptions(features = ["features"], glue = ["com.janavarro.war_of_suits.steps"])
class AndroidTestRunner : CucumberAndroidJUnitRunner()