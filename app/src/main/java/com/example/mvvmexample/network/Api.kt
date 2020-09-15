package com.example.mvvmexample.network

import com.example.mvvmexample.Model.response.User
import com.example.mvvmexample.Model.request.UserLoginReq
import com.example.mvvmexample.Model.response.BaseResponse
import com.example.mvvmexample.Model.response.Data
import com.example.mvvmexample.viewModel.billListBySearch.BaseResponses
import com.example.mvvmexample.viewModel.billListBySearch.BillListBySearchReq
import com.example.mvvmexample.viewModel.billListBySearch.BillListBySearchRes
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @POST("login")
    fun getLogin(@Body userLoginReq: UserLoginReq) : Call<User>

    @GET("users")
    fun getUser(@Query("page") page : Int) : Call<BaseResponse>

    @GET("users")
    fun getUserDetail(@Path("id") id : Int) : Call<Data>

    @POST("GetBillListBySearch")
    fun GetBillListBySearch(@Body billListBySearchReq: BillListBySearchReq): Call<BaseResponses<List<BillListBySearchRes>>>
}