package com.example.mvvmexample.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmexample.Reposistory.Userreposistory

class LoginViewModel : ViewModel() {
    var email: String? = "shubham"
    var password: String? = "shubham"
    var authListner: AuthListner? = null
    fun LoginButtonClick(view: View) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFailure("Enter Email Id Or Password")
            return
        }
           val loginResponse = Userreposistory().UserLogin(email!!, password!!)
            authListner?.onSuccess(loginResponse)
    }
    fun LoginWithFingerPrint(view : View)
    {
        authListner?.onStarted()
    }

}