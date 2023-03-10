package com.gun.hiltexample.data.repository

import android.util.Log
import com.gun.hiltexample.constant.Constants
import com.gun.hiltexample.data.dto.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) :
    UserDataSource {
    override suspend fun getUserList(): MutableList<User> {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val userList = appDatabase.userDao().getUserList()
            Log.d(Constants.TAG, "UserLocalDataSource - getUserListAsync() - userList : $userList")
            userList
        }
    }

    override suspend fun getUserListByIds(vararg userIds: Int): MutableList<User> {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val userList = appDatabase.userDao().getUserListByIds(userIds)
            Log.d(Constants.TAG, "UserLocalDataSource - getUserListByIds() - userList : $userList")
            userList
        }
    }

    override suspend fun getUserByName(name: String): User {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val user = appDatabase.userDao().getUserByName(name)
            Log.d(Constants.TAG, "UserLocalDataSource - getUserByName() - user : $user")
            user
        }
    }

    override suspend fun insertUsers(user: User): Long {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val result = appDatabase.userDao().insertUser(user) // 삽입된 항목의 새 rowId
            Log.d(Constants.TAG, "UserLocalDataSource - insertUsers() - users : $user, result : $result")
            result
        }
    }

    override suspend fun updateUser(user: User): Int {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val result = appDatabase.userDao().updateUser(user) // 성공적으로 삭제된 행 수 반환
            Log.d(Constants.TAG, "UserLocalDataSource - updateUser() - user : $user, result : $result")
            result
        }
    }

    override suspend fun deleteUser(user: User): Int {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val result = appDatabase.userDao().deleteUser(user) // 성공적으로 업데이트된 행 수 반환
            Log.d(Constants.TAG, "UserLocalDataSource - deleteUser() - user : $user, result : $result")
            result
        }
    }
}