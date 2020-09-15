package com.example.mvvmexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmexample.R
import com.example.mvvmexample.adapter.UserAdapter
import com.example.mvvmexample.viewModel.Home.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val adapter = UserAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        val itemViewModel = ViewModelProviders.of(this)
            .get(HomeViewModel::class.java)
        itemViewModel.userPagedList.observe(this, Observer {
        adapter.submitList(it)
        })
        recycler_view.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}