package com.android.example.project2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Movies::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun MovieDao() : MovieDao
}