package com.janavarro.war_of_suits.util

import android.util.Log
import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

object IdleResource : IdlingResource {

    private const val TAG = "IdleResource"
    private var _callback: IdlingResource.ResourceCallback? = null
    private val _isIdleNow: AtomicBoolean = AtomicBoolean(true)

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        return _isIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this._callback = callback
    }

    fun setIdleState(isIdleNow: Boolean) {
        _isIdleNow.set(isIdleNow)
        if(isIdleNow) {
            _callback?.onTransitionToIdle()
            Log.d(TAG, "resource on idle now")
        } else {
            Log.d(TAG, "resource busy now")
        }
    }

}