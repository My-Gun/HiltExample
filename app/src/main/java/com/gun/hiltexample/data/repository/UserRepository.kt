package com.gun.hiltexample.data.repository

import com.gun.hiltexample.data.dto.User
import com.gun.hiltexample.module.DataModule
import javax.inject.Inject

class UserRepository @Inject constructor(
//    @DataModule.UserLocalDataSourceQualifier
//    @DataModule.UserMockDataSourceQualifier
    @DataModule.UserProviderDataSourceQualifier
    private val userDataSource: UserDataSource
) {

    suspend fun getUserList(): MutableList<User> {
        return userDataSource.getUserList()
    }

    suspend fun getUserListByIds(userIds: IntArray): MutableList<User> {
        return userDataSource.getUserListByIds(*userIds)
    }

    suspend fun getUserByName(name: String): User? {
        return userDataSource.getUserByName(name)
    }

    suspend fun insertUsers(user: User): Long {
        return userDataSource.insertUsers(user)
    }

    suspend fun updateUser(user: User): Int {
        return userDataSource.updateUser(user)
    }

    suspend fun deleteUser(user: User): Int {
        return userDataSource.deleteUser(user)
    }
}