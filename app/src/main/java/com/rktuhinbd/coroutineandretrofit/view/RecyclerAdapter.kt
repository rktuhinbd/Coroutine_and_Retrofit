package com.rktuhinbd.coroutineandretrofit.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.coroutineandretrofit.databinding.RvItemUsersBinding
import com.rktuhinbd.coroutineandretrofit.model.UserResponse

class RecyclerAdapter(val users: List<UserResponse>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val binding = RvItemUsersBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        val data = users[position]

        holder.binding.tvName.text = data.name
        holder.binding.tvPhone.text = data.phone
        holder.binding.tvEmail.text = data.email
        holder.binding.tvAddress.text =
            "${data.address?.suite}, ${data.address?.street}, ${data.address?.city}, ${data.address?.zipcode}"
    }

    override fun getItemCount(): Int {
        return users.size
    }
}