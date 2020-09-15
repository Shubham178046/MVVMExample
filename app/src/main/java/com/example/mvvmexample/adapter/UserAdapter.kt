package com.example.mvvmexample.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmexample.Model.response.Data
import com.example.mvvmexample.R
import com.example.mvvmexample.ui.UserDetails
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : PagedListAdapter<Data, UserAdapter.ViewHolder>(USER_COMPARATOR) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemModel: Data) {
            itemView.tv_user_email.setText(itemModel.email)
            itemView.tv_user_name.setText(itemModel.first_name + " " + itemModel.last_name)
            Glide.with(itemView.context).load(itemModel.avatar).into(itemView.iv_avatar)
        }

    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
                newItem == oldItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        user?.let {
            holder.bind(user)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UserDetails::class.java)
            intent.putExtra("id", user?.id)
            holder.itemView.context.startActivity(intent)
        }
    }
}