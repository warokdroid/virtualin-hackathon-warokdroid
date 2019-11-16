package com.warokdroid.virtualin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.warokdroid.virtualin.R

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etFullName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRePassword: EditText
    private lateinit var btnCreateAccount: Button
    private lateinit var btnSignWithGoogle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.et_full_name)
        etUsername = findViewById(R.id.et_username)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        etRePassword = findViewById(R.id.et_repassword)
        btnCreateAccount = findViewById(R.id.btn_create_account)
        btnCreateAccount.setOnClickListener(this)
        btnSignWithGoogle = findViewById(R.id.btn_sign_with_google)
        btnSignWithGoogle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create_account -> {
                val verificationIntent =
                    Intent(this@RegisterActivity, VerificationActivity::class.java)
                startActivity(verificationIntent)
            }

            R.id.btn_sign_with_google -> {
                Toast.makeText(this, "Button sign in with Google clicked!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}