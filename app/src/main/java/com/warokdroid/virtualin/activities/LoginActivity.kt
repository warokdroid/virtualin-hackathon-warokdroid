package com.warokdroid.virtualin.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.warokdroid.virtualin.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvForgotPassword: TextView
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        initFirebase()
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

    private fun initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun login(email: String, password: String) {
        if (TextUtils.isEmpty(email))
            etEmail.error = "Email tidak boleh kosong"
        else if (TextUtils.isEmpty(password))
            etPassword.error = "Password tidak boleh kosong"
        else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@LoginActivity, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            val homeIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(homeIntent)
                            finish()
                            overridePendingTransition(
                                R.transition.slide_in_right,
                                R.transition.slide_out_left
                            )
                        } else
                            showMessageBox("Login failed. Your username and password is not matched")

                    }

                })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {
                val email: String = etEmail.text.toString().trim()
                val password: String = etPassword.text.toString().trim()
                login(email, password)
            }
            else -> {
                etEmail.error = "Email yang dimasukkan belum terdaftar"
                etPassword.error = "Password yang dimasukkan salah"
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

    private fun showMessageBox(message: String) {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Login")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.dismiss()
            }

        })
        alertDialogBuilder.show()
    }
}
