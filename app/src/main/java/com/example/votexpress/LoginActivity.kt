package com.example.votexpress

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvCreateAccount = findViewById<TextView>(R.id.tvCreateAccount)
        val forgotPassword = findViewById<TextView>(R.id.tvForgotPassword)

        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)


        forgotPassword.setOnClickListener {
            //Handle forgot password activity
            Toast.makeText(this, "Forgot password clicked!", Toast.LENGTH_SHORT).show()
        }

        tvCreateAccount.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if(validateInput(email, password)){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun validateInput(email: String, password: String): Boolean {
        if(TextUtils.isEmpty(email)) {
            emailEditText.error = "Please enter your email"
            emailEditText.requestFocus()
            return false
        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailEditText.error = "Please enter valid email"
//            emailEditText.requestFocus()
//            return false
//        }
        if(TextUtils.isEmpty(password)) {
            passwordEditText.error = "Please enter your password"
            passwordEditText.requestFocus()
            return false
        }

        return true
    }

}