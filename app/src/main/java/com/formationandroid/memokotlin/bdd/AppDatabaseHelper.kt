package com.formationandroid.memokotlin.bdd

import android.content.Context
import androidx.room.Room

class AppDatabaseHelper private constructor(context: Context) {
    private val database: AppDatabase

    companion object {
        private var databaseHelper: AppDatabaseHelper? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            if (databaseHelper == null) {
                databaseHelper = AppDatabaseHelper(
                    context.applicationContext
                )
            }
            return databaseHelper!!.database
        }
    }

    init {
        database = Room
            .databaseBuilder(context, AppDatabase::class.java, "memos.db")
            .allowMainThreadQueries()
            .build()
    }
}