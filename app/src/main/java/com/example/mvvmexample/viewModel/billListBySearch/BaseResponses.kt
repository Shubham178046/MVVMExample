package com.example.mvvmexample.viewModel.billListBySearch

data class BaseResponses<T>(
    val Message: String?,
    val ResponseData: T,
    val Status: Int
)