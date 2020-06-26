package com.formationandroid.memokotlin.bdd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemoDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun MemosDAO(): MemoDAO?
}