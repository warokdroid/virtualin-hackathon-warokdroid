package com.warokdroid.virtualin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.warokdroid.virtualin.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvForgotPassword: TextView
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }

    // Function to initialize the views
    private fun initViews() {
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        tvForgotPassword = findViewById(R.id.tv_forgot_password)
        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener(this)
        tvSignUp = findViewById(R.id.tv_sign_up)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                if (etEmail.text.toString().trim() == "warokdroid@gmail.com" && etPassword.text.toString().trim() == "WAROKDROID") {
                    val homeIntent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(homeIntent)
                    finish()
                    overridePendingTransition(
                        R.transition.slide_in_right,
                        R.transition.slide_out_left
                    )
                } else {
                    etEmail.error = "Email yang dimasukkan belum terdaftar"
                    etPassword.error = "Password yang dimasukkan salah"
                }
            }
        }
    }


    fun onForgotPassword(view: View) {
        Toast.makeText(this, "Forgot password clicked!", Toast.LENGTH_SHORT).show()
    }

    fun onSignUp(view: View) {
        val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(registerIntent)
    }
}
