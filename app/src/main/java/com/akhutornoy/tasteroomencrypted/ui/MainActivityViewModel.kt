package com.akhutornoy.tasteroomencrypted.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akhutornoy.tasteroomencrypted.db.User
import com.akhutornoy.tasteroomencrypted.db.UserDao
import com.akhutornoy.tasteroomencrypted.ui.base.BaseViewModel
import com.akhutornoy.tasteroomencrypted.ui.base.SingleLiveEvent
import com.akhutornoy.tasteroomencrypted.utils.applyProgressBar
import com.akhutornoy.tasteroomencrypted.utils.applySchedulers
import io.reactivex.Single

class MainActivityViewModel(private val userDao: UserDao) : BaseViewModel() {

    private val _onUserReadLiveData: MutableLiveData<User> by lazy { MutableLiveData<User>() }
    val onUserReadLiveData: LiveData<User>
        get() = _onUserReadLiveData

    private val _onUserInserted: SingleLiveEvent<Any> by lazy { SingleLiveEvent<Any>() }
    val onUserInserted: LiveData<Any>
        get() = _onUserInserted

    fun insertUser(user: User) {
        autoUnsubscribe(
            Single.fromCallable { userDao.insertUser(user) }
                .applySchedulers()
                .applyProgressBar(this)
                .subscribe(
                    { _onUserInserted.call() },
                    this::showError
                )
        )
    }

    fun readUser() {
        autoUnsubscribe(
            Single.fromCallable { userDao.getUser() ?: User() }
                .applySchedulers()
                .applyProgressBar(this)
                .subscribe(
                    { user -> _onUserReadLiveData.value = user },
                    this::showError
                )
        )
    }
}