package com.pranaysoni.randomusers.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import com.pranaysoni.randomusers.R


object DialogUtils {

    fun showProgressDialog(
        context: Context,
        message: String,
        isCancelable: Boolean? = false,
        isBGNeeded: Boolean = false
    ): Dialog {
        val progressDialog = Dialog(context)
        progressDialog.setContentView(R.layout.view_custom_progress_dialog)
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        progressDialog.findViewById<ProgressBar>(R.id.progressBarNormal)
        progressDialog.setCancelable(isCancelable!!)
        progressDialog.findViewById<AppCompatTextView>(R.id.tvTextMessage)?.text = message
        return progressDialog
    }
}