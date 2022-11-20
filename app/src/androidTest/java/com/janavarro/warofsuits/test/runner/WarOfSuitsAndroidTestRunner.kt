package com.janavarro.warofsuits.test.runner

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions

@CucumberOptions(glue = ["steps"], features = ["features"])
class WarOfSuitsAndroidTestRunner : CucumberAndroidJUnitRunner()