package com.gun.hiltexample.data.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.util.Log
import com.gun.hiltexample.constant.Constants
import com.gun.hiltexample.data.dto.User
import javax.inject.Inject

class UserProviderDataSource @Inject constructor(
    private val contentResolver: ContentResolver
) : UserDataSource {
    @SuppressLint("Range")
    override suspend fun getUserList(): MutableList<User> {
        val cursor = contentResolver.query(
            Constants.URI_EXAMPLE,
            arrayOf(AppDatabase.COL_USER_ID, AppDatabase.COL_USER_NAME), null, null,
            AppDatabase.COL_USER_ID
        )

        val userList = arrayListOf<User>()

        cursor?.let {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex(AppDatabase.COL_USER_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.COL_USER_NAME))
                userList.add(User(id, name))
            }

            cursor.close()
        }

        Log.d(Constants.TAG, "UserProviderDataSource - getUserList() - userList : $userList")

        return userList
    }

    @SuppressLint("Range")
    override suspend fun getUserListByIds(vararg userIds: Int): MutableList<User> {
        val cursor = contentResolver.query(
            Constants.URI_EXAMPLE,
            arrayOf(AppDatabase.COL_USER_ID, AppDatabase.COL_USER_NAME),
            AppDatabase.COL_USER_ID,
            userIds.map { it.toString() }.toTypedArray(),
            AppDatabase.COL_USER_ID
        )

        val userList = arrayListOf<User>()

        cursor?.let {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex(AppDatabase.COL_USER_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.COL_USER_NAME))
                userList.add(User(id, name))
            }

            cursor.close()
        }

        Log.d(
            Constants.TAG,
            "UserProviderDataSource - getUserListByIds() - userIds : $userIds, userList : $userList"
        )

        return userList
    }

    @SuppressLint("Range")
    override suspend fun getUserByName(name: String): User? {
        val cursor = contentResolver.query(
            Constants.URI_EXAMPLE,
            arrayOf(AppDatabase.COL_USER_NAME),
            AppDatabase.COL_USER_ID,
            arrayOf(name),
            AppDatabase.COL_USER_ID
        )

        var user: User? = null

        cursor?.let {
            if (cursor.moveToFirst()) {
                val id = cursor.getInt(cursor.getColumnIndex(AppDatabase.COL_USER_ID))
                val name = cursor.getString(cursor.getColumnIndex(AppDatabase.COL_USER_NAME))
                user = User(id, name)
            }
            cursor.close()
        }

        Log.d(Constants.TAG, "UserProviderDataSource - getUserByName() - user : $user")

        return user
    }

    override suspend fun insertUsers(user: User): Long {
        val rowValue = ContentValues()
        rowValue.put(AppDatabase.COL_USER_NAME, user.name)

        val uri = contentResolver.insert(
            Constants.URI_EXAMPLE,
            rowValue
        )

        val result = uri?.let {
            contentResolver.notifyChange(it, null)
            ContentUris.parseId(it)
        } ?: -1

        Log.d(Constants.TAG, "UserProviderDataSource - insertUsers() - result : $result")

        return result
    }

    override suspend fun updateUser(user: User): Int {
        val updateValues = ContentValues().apply {
            put(AppDatabase.COL_USER_NAME, user.name)
        }

        val selectionClause: String = AppDatabase.COL_USER_ID + "LIKE ?"
        val selectionArgs: Array<String> = arrayOf(user.userId.toString())

        val result: Int = contentResolver.update(
            Constants.URI_EXAMPLE,
            updateValues,
            selectionClause,
            selectionArgs
        )

        Log.d(Constants.TAG, "UserProviderDataSource - updateUser() - result : $result")

        return result
    }

    override suspend fun deleteUser(user: User): Int {
        val selectionClause: String = AppDatabase.COL_USER_ID + "LIKE ?"
        val selectionArgs: Array<String> = arrayOf(user.userId.toString())

        val result: Int = contentResolver.delete(
            Constants.URI_EXAMPLE,
            selectionClause,
            selectionArgs
        )

        Log.d(Constants.TAG, "UserProviderDataSource - updateUser() - result : $result")

        return result
    }
}