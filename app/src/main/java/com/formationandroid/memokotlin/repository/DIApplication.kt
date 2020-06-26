package com.formationandroid.memokotlin.repository

import android.app.Application

class DIApplication : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        //Dagger
        appComponent = DaggerAppComponent.builder().application(this).build()
    }

    companion object {
        private lateinit var instance: DIApplication
        fun getAppComponent(): AppComponent? {
            return instance.appComponent
        }
    }
}