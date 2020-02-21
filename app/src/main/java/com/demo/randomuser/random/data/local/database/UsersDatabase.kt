package com.demo.randomuser.random.data.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.randomuser.random.data.local.entities.RandomEntity


@Database(
    entities = [RandomEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "users.db"
        @Volatile
        private var INSTANCE: UsersDatabase? = null

        fun getInstance(@NonNull context: Context): UsersDatabase {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            UsersDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getUsersDao(): UsersDao

}