package com.example.votexpress

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FAQsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var faqAdapter: FAQAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_faqs)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.faqs_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerViewFAQ)

        val faqList = listOf(
            FAQModel("What is this app?", "This app allows you to vote for Prime Ministers and political parties."),
            FAQModel("How do I vote?", "Simply go to the voting section and select your preferred candidates."),
            FAQModel("Is my vote secure?", "Yes, all votes are encrypted and securely transmitted."),
            FAQModel("Can I change my vote?", "No, once submitted, the vote is final."),
            FAQModel("Where can I find the voting locations?", "Check the map section of the app for nearby voting centers.")
        )

        faqAdapter = FAQAdapter(faqList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = faqAdapter
    }
}