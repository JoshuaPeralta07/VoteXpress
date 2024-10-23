package com.example.votexpress

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

class VotePartyActivityNew : AppCompatActivity() {

    private lateinit var rvParty: RecyclerView
    private lateinit var partyAdapter: PartyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vote_party_new)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.votePartyActivityNew)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backBtn = findViewById<ImageView>(R.id.ivBtnBackNew)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitVote)

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        rvParty = findViewById(R.id.rvParty)

        val parties = listOf(
            Party("LOTR Party", "Gandalf"),
            Party("R&M Party", "Rick Sanchez"),
            Party("Wall Party", "Donald Trump"),
            Party("ACT Party", "David Seymour"),
            Party("National Party", "Christopher Luxon"),
            Party("New Zealand First", "Winston Peters"),
            Party("The Shire", "Frodo Baggins"),
            Party("The Green Party", "Snoop Dogg")
        )

        partyAdapter = PartyAdapter(parties)
        rvParty.layoutManager = LinearLayoutManager(this)
        rvParty.adapter = partyAdapter


        btnSubmit.setOnClickListener {
            val selectedParty = partyAdapter.getSelectedParty()
            if(selectedParty != null){
                Toast.makeText(this, "You voted for ${selectedParty.name}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, FeedbackActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please select a party", Toast.LENGTH_SHORT).show()
            }
        }


    }


}