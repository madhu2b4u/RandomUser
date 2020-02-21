package com.demo.randomuser.random.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.demo.randomuser.random.data.local.entities.RandomEntity

@Dao
abstract class UsersDao {
    @Query("SELECT * FROM RandomEntity WHERE id = :id")
    abstract suspend fun getUsers(id: String): RandomEntity?

    @Insert(onConflict = REPLACE)
    abstract fun insertUser(randomEntity: RandomEntity)
}