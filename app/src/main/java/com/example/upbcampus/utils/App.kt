package com.example.upbcampus.utils

import android.app.Application
import android.content.res.Resources
import com.example.upbcampus.MainActivity
import java.io.File

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        mResources = resources
        mFilesDir = filesDir
    }

    companion object {
        var mInstance: App? = null
            private set
        var mResources: Resources? = null
            private set
        var mFilesDir: File? = null
            private set
        var mActivity: MainActivity? = null
    }
}