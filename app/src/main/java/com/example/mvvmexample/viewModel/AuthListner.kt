package com.example.mvvmexample.viewModel

import androidx.lifecycle.LiveData
import com.example.mvvmexample.Model.response.User

interface AuthListner {
    fun onSuccess(loginResponse: LiveData<User>)
    fun onFailure(message : String)
    fun onStarted()
}