package com.gun.hiltexample.data.repository

import android.util.Log
import com.gun.hiltexample.constant.Constants
import com.gun.hiltexample.data.dto.User
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) :
    UserDataSource {
    override suspend fun getUserList(): MutableList<User> {
        val userList = appDatabase.userDao().getUserList()
        Log.d(Constants.TAG, "UserLocalDataSource - getUserListAsync() - userList : $userList")
        return userList
    }

    override suspend fun getUserListByIds(vararg userIds: Int): MutableList<User> {
        val userList = appDatabase.userDao().getUserListByIds(userIds)
        Log.d(Constants.TAG, "UserLocalDataSource - getUserListByIds() - userList : $userList")
        return userList
    }

    override suspend fun getUserByName(name: String): User {
        val user = appDatabase.userDao().getUserByName(name)
        Log.d(Constants.TAG, "UserLocalDataSource - getUserByName() - user : $user")
        return user
    }

    override suspend fun insertUsers(user: User): Long {
        val result = appDatabase.userDao().insertUser(user) // 삽입된 항목의 새 rowId
        Log.d(
            Constants.TAG,
            "UserLocalDataSource - insertUsers() - users : $user, result : $result"
        )
        return result
    }

    override suspend fun updateUser(user: User): Int {
        val result = appDatabase.userDao().updateUser(user) // 성공적으로 삭제된 행 수 반환
        Log.d(
            Constants.TAG,
            "UserLocalDataSource - updateUser() - user : $user, result : $result"
        )
        return result
    }

    override suspend fun deleteUser(user: User): Int {
        val result = appDatabase.userDao().deleteUser(user) // 성공적으로 업데이트된 행 수 반환
        Log.d(
            Constants.TAG,
            "UserLocalDataSource - deleteUser() - user : $user, result : $result"
        )
        return result
    }
}