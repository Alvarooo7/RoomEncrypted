package com.akhutornoy.tasteroomencrypted.ui.saveloaduser

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import com.akhutornoy.tasteroomencrypted.Injections
import com.akhutornoy.tasteroomencrypted.R
import com.akhutornoy.tasteroomencrypted.db.User
import com.github.ajalt.timberkt.Timber

import kotlinx.android.synthetic.main.activity_save_load_user.*
import kotlinx.android.synthetic.main.content_main.*

class SaveLoadUserActivity : AppCompatActivity() {

    private val viewModel: SaveLoadUserActivityViewModel by lazy { Injections.provideMainActivityViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_load_user)
        setSupportActionBar(toolbar)

        initViewModelObservers()
        initView()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        }
    }

    private fun initView() {
        save_button.setOnClickListener { onInsertClicked() }
        read_button.setOnClickListener { onReadClicked() }
    }

    private fun onInsertClicked() {
        val name = name_et.text.toString()
        val email = email_et.text.toString()
        val user = User(name, email)
        viewModel.insertUser(user)
    }

    private fun onReadClicked() {
        viewModel.readUser()
    }

    private fun initViewModelObservers() {
        viewModel.onUserReadLiveData.observe(this, Observer ( this::showUser ))
        viewModel.onUserInserted.observe(this, Observer {
            Toast.makeText(this, "Inserted User to DB", Toast.LENGTH_LONG).show()
        })
    }

    private fun showUser(user: User) {
        Timber.d { "showUser(): user=$user" }
        name_text_view.text = user.name
        email_text_view.text = user.email
    }
}
