package com.onedev.mygamesmultimodule.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.onedev.mygamesmultimodule.R
import java.text.DecimalFormat

@BindingAdapter("android:loadImage")
fun ImageView.loadImage(urlImage: String?) {
    try {
        load(urlImage) {
            crossfade(true)
            placeholder(R.drawable.img_placeholder_game)
        }
    } catch (e: Exception) {
        e.toString()
    }
}

fun View.gone() {
    visibility= View.GONE
}

fun View.visible() {
    visibility= View.VISIBLE
}

fun String.decimalFormat(): String {
    val number = java.lang.Double.valueOf(this)
    val dec = DecimalFormat("#,###,###")
    return dec.format(number)
}
