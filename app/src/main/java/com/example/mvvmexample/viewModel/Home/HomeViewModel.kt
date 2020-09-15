package com.example.mvvmexample.viewModel.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mvvmexample.Model.response.Data
import com.example.mvvmexample.datasource.UserDataSource
import com.example.mvvmexample.datasource.UserDataSourceFactory

class HomeViewModel : ViewModel() {
    val userPagedList : LiveData<PagedList<Data>>
    private val liveDataSource : LiveData<UserDataSource>

    init {
        val itemDataSourceFactory = UserDataSourceFactory()

        liveDataSource = itemDataSourceFactory.userLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()

        userPagedList = LivePagedListBuilder(itemDataSourceFactory,config)
            .build()

    }
}