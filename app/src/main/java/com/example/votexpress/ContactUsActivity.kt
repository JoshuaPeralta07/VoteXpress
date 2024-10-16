package com.example.votexpress

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactUsActivity : AppCompatActivity() {

    private lateinit var recipientEmail: TextView
    private lateinit var subject: EditText
    private lateinit var message: EditText
    private lateinit var btnSendEmail: Button
    private lateinit var phoneNumber: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_us)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.contactUs)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recipientEmail = findViewById(R.id.tvRecipientEmail)
        subject = findViewById(R.id.etSubject)
        message = findViewById(R.id.etMessage)
        btnSendEmail = findViewById(R.id.btnSend)
        phoneNumber = findViewById(R.id.tvPhoneNumber)

        btnSendEmail.setOnClickListener {
            sendEmail()
        }

        phoneNumber.setOnClickListener {
            val number = phoneNumber.text.toString()
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$number")
            startActivity(callIntent)
        }
    }

    private fun sendEmail() {
        val targetEmail = recipientEmail.text.toString()
        val subj = subject.text.toString().trim()
        val mes = message.text.toString().trim()

        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(targetEmail))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subj)
        mIntent.putExtra(Intent.EXTRA_TEXT, mes)

        try {
            startActivity(Intent.createChooser(mIntent, "Send Email"))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }


    }
}