package com.gun.hiltexample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gun.hiltexample.R
import com.gun.hiltexample.data.dto.User
import javax.inject.Inject

class UserRecyclerAdapter @Inject constructor() :
    ListAdapter<User, UserRecyclerAdapter.UserViewHolder>(userDifferCallback) {

    companion object {
        val userDifferCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem.userId == newItem.userId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.holder_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.tvName.text = currentList[position].name
    }

    override fun getItemCount() = currentList.size

    class UserViewHolder(view: View) : ViewHolder(view) {
        val tvName: TextView

        init {
            tvName = view.findViewById(R.id.tv_name)
        }
    }
}