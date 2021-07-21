package com.pranaysoni.randomusers.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pranaysoni.randomusers.base.BaseRecyclerViewAdapterBinding
import com.pranaysoni.randomusers.databinding.ItemUserDataBinding
import com.pranaysoni.randomusers.models.UserDataResult
import com.pranaysoni.randomusers.utils.loadImageFromUrlUsingGlide
import com.pranaysoni.randomusers.utils.setBirthDate
import com.pranaysoni.randomusers.utils.setUserNameWithGender

class UserDataAdapter(private val usersList:ArrayList<UserDataResult>):BaseRecyclerViewAdapterBinding<ItemUserDataBinding>(usersList) {
    override fun getDataBinding(viewType: Int, parent: ViewGroup): ItemUserDataBinding {
        return ItemUserDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<ItemUserDataBinding>, position: Int) {
        val userData = usersList[position]
        loadImageFromUrlUsingGlide(holder.dataBinding.ivUserImage, userData.picture.medium)
        holder.dataBinding.tvUserName.setUserNameWithGender(userData.name,userData.gender)
        holder.dataBinding.tvUserEmail.text = userData.email
        holder.dataBinding.tvUserDOB.setBirthDate(userData.dob.date)

    }
}