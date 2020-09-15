package com.gailgas.corporate.viewModel.billListBySearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.mvvmexample.viewModel.billListBySearch.BillListBySearchDataSource
import com.example.mvvmexample.viewModel.billListBySearch.BillListBySearchDataSourceFactory
import com.example.mvvmexample.viewModel.billListBySearch.BillListBySearchRes

class BillListBySearchViewModel() : ViewModel() {
    companion object {
        var searchs: String? = null
    }

    var billListBySearch: LiveData<PagedList<BillListBySearchRes>>? = null
    var livebillListBySearch: LiveData<PageKeyedDataSource<Int, BillListBySearchRes>>? = null

    init {
        val itemBillHistoryDataSourceFactory = BillListBySearchDataSourceFactory(searchs!!)
        livebillListBySearch = itemBillHistoryDataSourceFactory!!.getItemLiveDataSource()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(150)
            .setInitialLoadSizeHint(BillListBySearchDataSource.PAGE_SIZE * 2)
            .setEnablePlaceholders(true)
            .setPageSize(BillListBySearchDataSource.PAGE_SIZE)
            .build()

        billListBySearch = LivePagedListBuilder(itemBillHistoryDataSourceFactory, config).build()

    }
}