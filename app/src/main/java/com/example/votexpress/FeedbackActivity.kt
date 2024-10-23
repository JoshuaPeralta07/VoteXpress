package com.example.votexpress

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FeedbackActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var commentsEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_feedback)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.feedbackActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ratingBar = findViewById(R.id.ratingBar)
        commentsEditText = findViewById(R.id.et_comments)
        submitButton = findViewById(R.id.btnSubmitFeedback)

        submitButton.setOnClickListener {
            submitFeedback()
        }
    }

    private fun submitFeedback() {
        val rating = ratingBar.rating
        val comments = commentsEditText.text.toString()

        //Saved to sharedPreferences to store data locally without DB
        saveFeedbackToPreferences(rating, comments)
        Toast.makeText(this, "Feedback submitted! Rating: $rating", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, FinalActivity::class.java)
        startActivity(intent)
        finish()

        ratingBar.rating = 3.0f
        commentsEditText.text.clear()
    }

    private fun saveFeedbackToPreferences(rating: Float, comments: String) {
        val sharedPref = getSharedPreferences("FeedbackPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putFloat("rating", rating)
        editor.putString("comments", comments)
        editor.apply()

    }
}