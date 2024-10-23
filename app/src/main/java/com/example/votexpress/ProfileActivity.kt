package com.example.votexpress

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var fullNameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var voterIdTextView: TextView
    private lateinit var changeButton: Button

    private lateinit var pickImage: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profileActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backBtn = findViewById<ImageView>(R.id.ivBtn_Back)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        profileImageView = findViewById(R.id.iv_profile_picture)
        fullNameTextView = findViewById(R.id.tvFullName)
        emailTextView = findViewById(R.id.tv_user_email)
        voterIdTextView = findViewById(R.id.tv_user_voterId)
        changeButton = findViewById(R.id.btn_change_picture)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedFullName = sharedPreferences.getString("fullName", null)
        val savedEmail = sharedPreferences.getString("email", null)
        val savedVoterId = sharedPreferences.getString("voterId", null)

        fullNameTextView.text = savedFullName
        emailTextView.text = savedEmail
        voterIdTextView.text = savedVoterId

        pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                profileImageView.setImageURI(uri) // Set the selected image as the profile picture
            } else {
                Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show()
            }
        }

        changeButton.setOnClickListener {
            openGallery()
        }


    }

    private fun openGallery() {
        pickImage.launch("image/")
    }


}