package com.warokdroid.virtualin.activities

import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.warokdroid.virtualin.R

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etFullName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRePassword: EditText
    private lateinit var btnCreateAccount: Button
    private lateinit var btnSignWithGoogle: Button

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        initFirebase()
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

    private fun initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create_account -> {
                val email: String = etEmail.text.toString().trim()
                val password: String = etPassword.text.toString().trim()
                signUp(email, password)
            }

            R.id.btn_sign_with_google -> {
                Toast.makeText(this, "Button sign in with Google clicked!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun signUp(email: String, password: String) {
        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Email tidak boleh kosong"
        } else if (TextUtils.isEmpty(password)) {
            etPassword.error = "Password tidak boleh kosong"
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            val alertDialogBuilder: AlertDialog.Builder =
                                AlertDialog.Builder(this@RegisterActivity)
                            alertDialogBuilder.setTitle("Sign Up")
                            alertDialogBuilder.setMessage("Your account has been registered. Please sign in use your username and password.")
                            alertDialogBuilder.setPositiveButton(
                                "OK",
                                object : DialogInterface.OnClickListener {
                                    override fun onClick(dialog: DialogInterface?, which: Int) {
                                        this@RegisterActivity.finish()
                                    }
                                })
                            alertDialogBuilder.setOnCancelListener(object :
                                DialogInterface.OnCancelListener {
                                override fun onCancel(dialog: DialogInterface?) {
                                    this@RegisterActivity.finish()
                                }
                            })
                            alertDialogBuilder.show()
                        } else {
                            p0.exception?.printStackTrace()
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registered has been failed! Please try again.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                })
        }
    }
}