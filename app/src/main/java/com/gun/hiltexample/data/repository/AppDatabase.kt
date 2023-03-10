package com.gun.hiltexample.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gun.hiltexample.data.dao.UserDao
import com.gun.hiltexample.data.dto.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val TABLE_NAME = "user"
        const val COL_USER_ID = "userId"
        const val COL_USER_NAME = "name"
    }

    abstract fun userDao(): UserDao
}