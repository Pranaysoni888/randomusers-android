package com.pranaysoni.randomusers.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.pranaysoni.randomusers.R
import com.pranaysoni.randomusers.utils.DialogUtils
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<V : BaseViewModel, B : ViewDataBinding> : AppCompatActivity(){


    abstract val modelClass: KClass<V>
    protected abstract val layoutId: Int
    protected lateinit var binding: B
    lateinit var viewModel: V
    private var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        createViewModel(savedInstanceState)
        getDataFromIntent()
        initControls()
        listeners()
        addObserver()
        initSetup()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.writeTo(outState)
    }

    open fun addObserver() {}
    open fun removeObservers() {}
    protected abstract fun listeners()
    protected abstract fun initControls()
    protected open fun getDataFromIntent() {}
    protected open fun initSetup() {}

    override fun onDestroy() {
        super.onDestroy()
        hideProgressDialog()
        removeObservers()
    }

    private fun createViewModel(savedInstanceState: Bundle?) {
        this.viewModel = getViewModel(clazz = modelClass)
        this.viewModel.readFrom(savedInstanceState)
    }


    fun showProgressDialog(
        mContext: Context,
        title: String = "",
        message: String = getString(R.string.default_loading_message),
        isBGNeeded: Boolean = false
    ) {
        progressDialog = DialogUtils.showProgressDialog(this, message, false, isBGNeeded)
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            } else {
                it.show()
            }
        }
    }

    fun hideProgressDialog() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    fun showSnackBar(message:String){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

}
