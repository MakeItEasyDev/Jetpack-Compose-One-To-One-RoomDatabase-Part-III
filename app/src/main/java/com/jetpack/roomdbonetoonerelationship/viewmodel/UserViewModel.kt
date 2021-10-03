package com.jetpack.roomdbonetoonerelationship.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.jetpack.roomdbonetoonerelationship.database.UserDatabase
import com.jetpack.roomdbonetoonerelationship.entity.Library
import com.jetpack.roomdbonetoonerelationship.entity.User
import com.jetpack.roomdbonetoonerelationship.entity.UserAndLibrary
import com.jetpack.roomdbonetoonerelationship.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val _readAllData = MutableLiveData<List<UserAndLibrary>>()
    var readAllData: LiveData<List<UserAndLibrary>> = _readAllData
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _readAllData.postValue(repository.getUserData(userId))
        }
    }

    fun addUser(item: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(item)
        }
    }

    fun addLibrary(item: List<Library>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLibrary(item)
        }
    }
}

class UserViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(application) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}


















