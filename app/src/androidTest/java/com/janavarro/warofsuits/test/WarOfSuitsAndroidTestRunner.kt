package com.janavarro.warofsuits.test

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions

@CucumberOptions(glue = ["com.janavarro.warofsuits.steps"], features = ["features"])
class WarOfSuitsAndroidTestRunner : CucumberAndroidJUnitRunner()