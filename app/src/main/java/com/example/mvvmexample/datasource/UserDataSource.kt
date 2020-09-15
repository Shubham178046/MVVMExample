package com.example.mvvmexample.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.mvvmexample.Model.response.BaseResponse
import com.example.mvvmexample.Model.response.Data
import com.example.mvvmexample.Model.response.User
import com.example.mvvmexample.network.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserDataSource : PageKeyedDataSource<Int, Data>() {
    val TAG = UserDataSource.javaClass.simpleName.toString()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Data>
    ) {
        Service.getSerive().getUser(FIRST_PAGE).enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.localizedMessage)
            }

            override fun onResponse(
                call: Call<BaseResponse>,
                response: Response<BaseResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val apiResponse = response.body()!!
                        val responseItems = apiResponse.data
                        responseItems?.let {
                            callback.onResult(responseItems, null, FIRST_PAGE + 1)
                        }
                    }
                }
            }

        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        Service.getSerive().getUser(params.key).enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val apiResponse = response.body()!!
                        val responseItems = apiResponse.data
                        val key = params.key + 1
                        responseItems?.let {
                            callback.onResult(responseItems, key)
                        }
                    }
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        Service.getSerive().getUser(params.key).enqueue(object : Callback<BaseResponse>{
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.localizedMessage)
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.body() != null) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    val key = if (params.key > 1 )params.key -1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

        })
    }

    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1

    }
}
