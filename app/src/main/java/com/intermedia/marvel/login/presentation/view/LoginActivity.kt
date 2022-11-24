package com.intermedia.marvel.login.presentation.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import com.intermedia.marvel.R
import com.intermedia.marvel.databinding.ActivityLoginBinding
import com.intermedia.marvel.home.presentation.view.HomeActivity
import com.intermedia.marvel.login.presentation.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val model: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var sharedPref = applicationContext.getSharedPreferences("User_State", Context.MODE_PRIVATE)
        if (sharedPref != null) {
            if (sharedPref.getBoolean("isLoged", false)) {
                goToHomeActivity()
            }
        }
        binding.loginPasswordField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
                // For now it's not necessary
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.length >= 5) {
                        binding.loginButton.isEnabled = true
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // For now it's not necessary
            }

        })

        model.error.observe(this) { showToast() }
        model.loginOk.observe(this) { saveUserState() }

        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            if (binding.loginMailField.text!!.isNotEmpty() && binding.loginPasswordField.text!!.isNotEmpty()) {
                model.setupLoginButton(
                    binding.loginMailField.text.toString(),
                    binding.loginPasswordField.text.toString()
                )
            } else {
                showToast()
            }
        }
    }

    fun saveUserState() {
        var sharedPref = applicationContext.getSharedPreferences("User_State", Context.MODE_PRIVATE)
        sharedPref.edit { putBoolean("isLoged", true).commit() }
        goToHomeActivity()
    }

    fun goToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showToast() {
        binding.loginMailFieldContainer.error = "error"
        binding.loginPasswordFieldContainer.error = "error"
        val toast = Toast(applicationContext)
        toast.apply {
            setText("The combination of Email and Password is not correct. Please retry.")
            Toast.LENGTH_SHORT
        }.show()
    }

}