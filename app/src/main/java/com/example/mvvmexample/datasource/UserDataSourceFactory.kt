package com.example.mvvmexample.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mvvmexample.Model.response.Data

class UserDataSourceFactory : DataSource.Factory<Int, Data>() {
    val userLiveDataSource = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, Data> {
        val userDataSource =
            UserDataSource()
        userLiveDataSource.postValue(userDataSource)

        return userDataSource
    }

}