package com.akhutornoy.tasteroomencrypted.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhutornoy.tasteroomencrypted.db.User
import com.akhutornoy.tasteroomencrypted.db.UserDao
import com.akhutornoy.tasteroomencrypted.ui.base.SingleLiveEvent

class MainActivityViewModel(private val userDao: UserDao) : ViewModel(){

    private val _onUserReadLiveData: MutableLiveData<User> by lazy { MutableLiveData<User>() }
    val onUserReadLiveData: LiveData<User>
        get() = _onUserReadLiveData

    private val _onUserInserted: SingleLiveEvent<Any> by lazy { SingleLiveEvent<Any>() }
    val onUserInserted: LiveData<Any>
        get() = _onUserInserted

    fun insertUser(user: User) {
        userDao.insertUser(user)
        _onUserInserted.call()
    }

    fun readUser() {
        val user = userDao.getUser()
        _onUserReadLiveData.value = user ?: User()
    }
}