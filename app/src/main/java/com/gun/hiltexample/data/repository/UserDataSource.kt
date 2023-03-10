package com.gun.hiltexample.data.repository

import com.gun.hiltexample.data.dto.User

interface UserDataSource {
    suspend fun getUserList(): MutableList<User>

    suspend fun getUserListByIds(vararg userIds: Int): MutableList<User>

    suspend fun getUserByName(name: String): User?

    suspend fun insertUsers(user: User): Long

    suspend fun updateUser(user: User): Int

    suspend fun deleteUser(user: User): Int
}