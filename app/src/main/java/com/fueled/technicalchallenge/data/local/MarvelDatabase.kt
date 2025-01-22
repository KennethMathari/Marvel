package com.fueled.technicalchallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fueled.technicalchallenge.data.local.dao.CharacterDao
import com.fueled.technicalchallenge.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class], version = 1, exportSchema = false
)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: MarvelDatabase? = null

        fun getDatabase(context: Context): MarvelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, MarvelDatabase::class.java, "marvel_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}