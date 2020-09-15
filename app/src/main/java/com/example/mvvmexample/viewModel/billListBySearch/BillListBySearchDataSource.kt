package com.example.mvvmexample.viewModel.billListBySearch

import android.content.Context
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.mvvmexample.network.RetroFitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BillListBySearchDataSource(var baseActivity: Context? , var search : String) : PageKeyedDataSource<Int, BillListBySearchRes>() {
    val TAG = "GAILGAS"

    companion object {
        const val PAGE_SIZE = 5
        const val FIRST_PAGE = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BillListBySearchRes>
    ) {
        var billListBySearchReq: BillListBySearchReq = BillListBySearchReq()
        billListBySearchReq.GAId = 1
        billListBySearchReq.PageNo = FIRST_PAGE
        billListBySearchReq.UserId = 10331
        billListBySearchReq.Search = search

        RetroFitService.getService().GetBillListBySearch(billListBySearchReq).enqueue(object  : Callback<BaseResponses<List<BillListBySearchRes>>>{
            override fun onFailure(
                call: Call<BaseResponses<List<BillListBySearchRes>>>,
                t: Throwable
            ) {
                Log.d(TAG, "onFailure: " + t.localizedMessage)
            }

            override fun onResponse(
                call: Call<BaseResponses<List<BillListBySearchRes>>>,
                response: Response<BaseResponses<List<BillListBySearchRes>>>
            ) {
                if (response.body() != null) {
                    callback.onResult(response.body()!!.ResponseData, null, FIRST_PAGE + 1)
                }
            }

        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BillListBySearchRes>
    ) {
        var billListBySearchReq: BillListBySearchReq = BillListBySearchReq()
        billListBySearchReq.GAId =1
        billListBySearchReq.PageNo =  params.key
        billListBySearchReq.UserId =10331
        billListBySearchReq.Search = search

        RetroFitService.getService().GetBillListBySearch(billListBySearchReq).enqueue(object  : Callback<BaseResponses<List<BillListBySearchRes>>>{
            override fun onFailure(
                call: Call<BaseResponses<List<BillListBySearchRes>>>,
                t: Throwable
            ) {
                Log.d(TAG, "onFailure: " + t.localizedMessage)
            }

            override fun onResponse(
                call: Call<BaseResponses<List<BillListBySearchRes>>>,
                response: Response<BaseResponses<List<BillListBySearchRes>>>
            ) {
                if (response.body() != null) {
                    callback.onResult(response.body()!!.ResponseData, params.key + 1)
                }
            }

        })

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, BillListBySearchRes>
    ) {
        var billListBySearchReq: BillListBySearchReq = BillListBySearchReq()
        billListBySearchReq.GAId = 1
        billListBySearchReq.PageNo =  params.key
        billListBySearchReq.UserId = 10331
        billListBySearchReq.Search = search

        RetroFitService.getService().GetBillListBySearch(billListBySearchReq).enqueue(object  : Callback<BaseResponses<List<BillListBySearchRes>>>{
            override fun onFailure(
                call: Call<BaseResponses<List<BillListBySearchRes>>>,
                t: Throwable
            ) {
                Log.d(TAG, "onFailure: " + t.localizedMessage)
            }

            override fun onResponse(
                call: Call<BaseResponses<List<BillListBySearchRes>>>,
                response: Response<BaseResponses<List<BillListBySearchRes>>>
            ) {
                val adjacentKey = if (params.key > 1) params.key - 1 else null
                if (response.body() != null) {
                    callback.onResult(response.body()!!.ResponseData, adjacentKey)
                }

            }

        })
    }
}