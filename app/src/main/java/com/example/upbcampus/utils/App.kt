package com.example.upbcampus.utils

import android.app.Application
import android.content.res.Resources

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mResources = resources
    }

    companion object {
        var mInstance: App? = null
            private set
        var mResources: Resources? = null
            private set
    }
}