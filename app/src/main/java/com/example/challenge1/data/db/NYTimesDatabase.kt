package com.example.challenge1.data.db

import android.content.Context
import androidx.room.*
import com.example.challenge1.data.db.entity.Result

@Database(entities = [Result::class],
version = 1)

abstract class NYTimesDatabase:RoomDatabase() {

    abstract fun currentArticleDao(): CurrentArticleDao

    companion object {
        @Volatile private var instance: NYTimesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NYTimesDatabase::class.java, "NYTimes.db")
                .build()
    }
}