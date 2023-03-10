package com.gun.hiltexample.data.repository

import com.gun.hiltexample.data.dto.User
import javax.inject.Inject

class UserMockDataSource @Inject constructor() : UserDataSource {
    override suspend fun getUserList(): MutableList<User> {
        return arrayListOf(User(1, "홍길동(Mock)"), User(2, "김길동(Mock)"))
    }

    override suspend fun getUserListByIds(vararg userIds: Int): MutableList<User> {
        val userList = arrayListOf<User>()

        for (userId in userIds) {
            userList.add(User(userId, "홍길동(Mock)"))
        }

        return userList
    }

    override suspend fun getUserByName(name: String): User {
        return User(1, name)
    }

    override suspend fun insertUsers(user: User): Long {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User): Int {
        TODO("Not yet implemented")
    }
}