package com.example.mvvmexample.viewModel.billListBySearch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.mvvmexample.ui.MainActivity.Companion.mActivity

class BillListBySearchDataSourceFactory(var search: String) :
    DataSource.Factory<Int, BillListBySearchRes>() {
    val LiveDataSource: MutableLiveData<PageKeyedDataSource<Int, BillListBySearchRes>> =
        MutableLiveData<PageKeyedDataSource<Int, BillListBySearchRes>>()
    internal var baseActivity: Context? = null
    override fun create(): DataSource<Int, BillListBySearchRes> {
        baseActivity = mActivity
        val itemDataSource = BillListBySearchDataSource(baseActivity, search)
        LiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, BillListBySearchRes>> {
        return LiveDataSource
    }
}