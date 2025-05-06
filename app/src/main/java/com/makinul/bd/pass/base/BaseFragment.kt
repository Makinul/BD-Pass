package com.makinul.bd.pass.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {


    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}