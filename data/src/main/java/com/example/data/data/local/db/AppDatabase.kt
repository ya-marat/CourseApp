package com.example.data.data.local.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        private var db: AppDatabase? = null
        private const val DB_NAME = "courses_app_database.db"
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =  Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun appDatabaseDao(): DatabaseDao
}