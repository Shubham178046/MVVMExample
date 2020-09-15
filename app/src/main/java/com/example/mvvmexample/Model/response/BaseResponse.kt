package com.example.mvvmexample.Model.response

open class BaseResponse
{
    val ad: Ad?=null
    val `data`: List<Data>?=null
    val page: Int?=null
    val per_page: Int?=null
    val total: Int?=null
    val total_pages: Int?=null
}