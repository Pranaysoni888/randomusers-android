package com.pranaysoni.randomusers.ui.activities

import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.pranaysoni.randomusers.R
import com.pranaysoni.randomusers.base.BaseActivity
import com.pranaysoni.randomusers.databinding.ActivityHomeScreenBinding
import com.pranaysoni.randomusers.models.UserDataResult
import com.pranaysoni.randomusers.network.Resource
import com.pranaysoni.randomusers.ui.adapters.UserDataAdapter
import com.pranaysoni.randomusers.utils.hideView
import com.pranaysoni.randomusers.utils.showToast
import com.pranaysoni.randomusers.utils.showView
import com.pranaysoni.randomusers.viewmodels.HomeScreenViewModel
import kotlin.reflect.KClass

class HomeScreenActivity : BaseActivity<HomeScreenViewModel,ActivityHomeScreenBinding>() {

    override val modelClass: KClass<HomeScreenViewModel>
        get() = HomeScreenViewModel::class
    override val layoutId: Int
        get() = R.layout.activity_home_screen

    private lateinit var userDataAdapter: UserDataAdapter
    private val userDataList = ArrayList<UserDataResult>()

    private val usersListObserver = Observer<Any> { response ->
        when (response) {

            is Resource.Error<*> -> {
                showToast(this, response.message.toString())
            }
            is Resource.Success<*> -> {
                val res = response.data as ArrayList<UserDataResult>
                userDataList.clear()
                res.let {
                    userDataList.addAll(it)
                }
                binding.rvUserData.adapter?.notifyDataSetChanged()

                if(userDataList.isEmpty()){
                    binding.rvUserData.hideView()
                    binding.tvNoUsersFound.showView()
                }else{
                    binding.rvUserData.showView()
                    binding.tvNoUsersFound.hideView()
                }

            }
            is Resource.Loading<*> -> {

                response.isLoadingShow.let {
                    if (it as Boolean) {
                        showSnackBar(getString(R.string.msg_list_updating))
                    } else {
                        showSnackBar(getString(R.string.msg_list_updated))
                    }
                }
            }
        }
    }

    override fun listeners() {
        binding.swipeRefreshUsers.setOnRefreshListener {
            binding.swipeRefreshUsers.isRefreshing = false
            fetchUsersList()
        }
    }

    override fun initControls() {
        userDataAdapter = UserDataAdapter(userDataList)
        binding.rvUserData.adapter = userDataAdapter
        fetchUsersList()
    }

    override fun initSetup() {
        //fetchUsersList()
    }

    override fun addObserver() {
        viewModel.cityListStatus.observe(this,usersListObserver)
    }

    override fun removeObservers() {
        viewModel.cityListStatus.removeObserver(usersListObserver)
    }

    fun fetchUsersList(){
        viewModel.getUsersList()
    }
}