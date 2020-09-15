package com.example.mvvmexample.Reposistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmexample.Model.request.UserLoginReq
import com.example.mvvmexample.Model.response.User
import com.example.mvvmexample.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Userreposistory {
val TAG = "UserReposistory"
    fun UserLogin( email : String,  password : String) : LiveData<User>
    {
        var userLoginReq : UserLoginReq = UserLoginReq()
        userLoginReq.email = email
        userLoginReq.password = password
        val loginResponse = MutableLiveData<User>()
        Service.getSerive().getLogin(userLoginReq).enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(TAG, "onFailure: "+t.localizedMessage)
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful)
                {
                    if(response.body() !=null)
                    {
                        if(response.body()!!.token.isNotEmpty())
                        {
                            loginResponse.value = response.body()
                        }
                    }
                }
            }

        })
        return loginResponse
    }
}