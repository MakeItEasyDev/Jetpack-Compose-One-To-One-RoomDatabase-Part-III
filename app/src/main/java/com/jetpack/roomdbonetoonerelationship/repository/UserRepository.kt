package com.jetpack.roomdbonetoonerelationship.repository

import androidx.lifecycle.LiveData
import com.jetpack.roomdbonetoonerelationship.dao.UserDao
import com.jetpack.roomdbonetoonerelationship.entity.Library
import com.jetpack.roomdbonetoonerelationship.entity.User
import com.jetpack.roomdbonetoonerelationship.entity.UserAndLibrary

class UserRepository(private val userDao: UserDao) {
    var readAllData: LiveData<List<UserAndLibrary>>? = null

    suspend fun addUser(item: List<User>) {
        userDao.insertUser(item = item)
    }

    suspend fun addLibrary(item: List<Library>) {
        userDao.insertLibrary(item = item)
    }

    fun getUserData(userId: Int): List<UserAndLibrary> {
        return userDao.getUserAndLibraries(userId = userId)
    }
}