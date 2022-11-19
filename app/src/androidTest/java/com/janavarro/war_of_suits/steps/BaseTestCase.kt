package com.janavarro.war_of_suits.steps
import androidx.annotation.CallSuper
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import com.janavarro.war_of_suits.util.IdleResource
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.concurrent.TimeUnit

abstract class BaseTestCase: TestCase() {

    @CallSuper
    open fun before() {
        IdlingRegistry.getInstance().register(IdleResource)
        IdlingPolicies.setMasterPolicyTimeout(12, TimeUnit.MINUTES)
        IdlingPolicies.setIdlingResourceTimeout(10, TimeUnit.MINUTES)
    }

    @CallSuper
    open fun after() {
        IdlingRegistry.getInstance().unregister(IdleResource)
        IdlingPolicies.setMasterPolicyTimeout(60, TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout(27, TimeUnit.SECONDS)
    }
}