package com.formationandroid.memokotlin.repository

import android.app.Application
import android.content.Context
import com.formationandroid.memokotlin.bdd.AppDatabaseHelper
import com.formationandroid.memokotlin.bdd.MemoDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideMainRepository(): MainRepository? {
        return MainRepository()
    }

    @Singleton
    @Provides
    fun provideMemoDAO(applicationContext: Context): MemoDAO {
        return AppDatabaseHelper.getDatabase(applicationContext).MemosDAO()!!
    }
}