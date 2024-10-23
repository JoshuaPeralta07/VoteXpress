package com.example.votexpress

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VotePMActivity : AppCompatActivity() {

    private lateinit var recyclerViewPM: RecyclerView
    private lateinit var candidateAdapter: CandidateAdapter
    private lateinit var btnSubmit: Button
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vote_pm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.votePMActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<ImageView>(R.id.backButtonPM)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        recyclerViewPM = findViewById(R.id.recyclerViewPM)
        btnSubmit = findViewById(R.id.btn_submit_vote_pm)

        val candidates = listOf(
            Candidate("Gandalf The Grey", "LOTR Party"),
            Candidate("Rick Sanchez", "R&M Party"),
            Candidate("Donald Trump", "Wall Party"),
            Candidate("David Seymour", "ACT Party"),
            Candidate("Christopher Luxon", "National Party"),
            Candidate("Winston Peters", "New Zealand First"),
            Candidate("Frodo Baggins", "The Shire"),
            Candidate("Snoop Dogg", "The Green Party")
        )

        candidateAdapter = CandidateAdapter(candidates)
        recyclerViewPM.layoutManager = LinearLayoutManager(this)
        recyclerViewPM.adapter = candidateAdapter

        btnSubmit.setOnClickListener {
            val sharedPreferences = getSharedPreferences("VotingPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val selectedCandidate = candidateAdapter.getSelectedCandidate()
            if (selectedCandidate != null) {
                editor.putString("candidate", selectedCandidate.name)
                editor.putBoolean("hasVotedForPM", true)
                editor.apply()
                Toast.makeText(this, "You voted for ${selectedCandidate.name}", Toast.LENGTH_SHORT)
                    .show()
                // navigate to feedback
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please select a candidate", Toast.LENGTH_SHORT).show()
            }
        }
    }
}