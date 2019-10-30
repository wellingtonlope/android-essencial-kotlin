package br.com.livroandroid.carros.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(activity, message, length).show()

fun Fragment.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(activity, message, length).show()