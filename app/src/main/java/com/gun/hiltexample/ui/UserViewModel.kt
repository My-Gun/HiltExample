package com.gun.hiltexample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gun.hiltexample.data.dto.User
import com.gun.hiltexample.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    val userLiveData = MutableLiveData<List<User>>()
    val insertResultLiveData = MutableLiveData<Long>()

    fun getUserList() = viewModelScope.launch {
        userLiveData.value = userRepository.getUserList()
    }

    fun insertUser(user: User) = viewModelScope.launch {
        insertResultLiveData.value = userRepository.insertUsers(user)
    }
}