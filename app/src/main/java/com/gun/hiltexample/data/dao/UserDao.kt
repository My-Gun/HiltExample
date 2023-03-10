package com.gun.hiltexample.data.dao

import androidx.room.*
import com.gun.hiltexample.data.dto.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getUserList(): MutableList<User>

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    suspend fun getUserListByIds(userIds: IntArray): MutableList<User>

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    suspend fun getUserByName(name: String): User

    @Insert
    suspend fun insertUser(users: User): Long

    @Update
    suspend fun updateUser(user: User): Int

    @Delete
    suspend fun deleteUser(user: User): Int
}