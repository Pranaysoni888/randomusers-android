package com.pranaysoni.randomusers.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.pranaysoni.randomusers.R
import com.pranaysoni.randomusers.models.Name
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun String?.nullSafe(defaultValue: String = ""): String {
    return this ?: defaultValue
}

fun Int?.nullSafe(defaultValue: Int = 0): Int {
    return this ?: defaultValue
}

fun Long?.nullSafe(defaultValue: Long = 0L): Long {
    return this ?: defaultValue
}

fun Double?.nullSafe(defaultValue: Double = 0.0): Double {
    return this ?: defaultValue
}

fun Boolean?.nullSafe(defaultValue: Boolean = false): Boolean {
    return this ?: defaultValue
}

fun File?.nullSafe(): File? {
    return this
}

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}


fun Any.showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).apply { show() }
}

fun AppCompatEditText.getValue(): String {
    return text.toString().trim()
}

fun loadImageFromUrlUsingGlide(imageView: ImageView, url: String?, isDefaultSet: Boolean? = true) {
    if (!url.isNullOrEmpty())
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    else {
        if (isDefaultSet!!)
            Glide.with(imageView.context).load(R.drawable.ic_placeholder).into(imageView)

    }
}

fun AppCompatTextView.setUserNameWithGender(userName: Name, gender: String){
    text = "${userName.first} ${userName.last} - $gender"
}

fun AppCompatTextView.setBirthDate(dob: String){
    text = SimpleDateFormat(DATE_DD_MM_YYYY).format(SimpleDateFormat(DATE_UTC_YYYY_MM_DD_HH_MM_SS_Z, Locale.ENGLISH).parse(dob))
}
