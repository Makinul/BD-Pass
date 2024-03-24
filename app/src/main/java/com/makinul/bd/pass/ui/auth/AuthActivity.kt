package com.makinul.bd.pass.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.makinul.bd.pass.base.BaseActivity
import com.makinul.bd.pass.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}