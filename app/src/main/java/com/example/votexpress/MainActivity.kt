package com.example.votexpress

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        setSupportActionBar(toolbar)

        navigationView.bringToFront()
        drawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        navigationView.setNavigationItemSelectedListener(this)

        val btnVotePM = findViewById<Button>(R.id.button_vote_pm)
        val btnVoteParty = findViewById<Button>(R.id.button_vote_party)

        btnVotePM.setOnClickListener {
            val intent = Intent(this, VotePMActivity::class.java)
            startActivity(intent)
        }

        btnVoteParty.setOnClickListener {
            val intent = Intent(this, VotePartyActivityNew::class.java)
            startActivity(intent)
        }


        // Handle the back press using onBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    // Call the default back pressed behavior
                    isEnabled = false // Disable this callback to avoid an infinite loop
                    onBackPressedDispatcher.onBackPressed() // Handle the back press as usual
                }
            }
        })


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile_menu -> {
                //Handle Profile
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }

            R.id.faqs_menu -> {
                //Handle FAQS
                val intent = Intent(this, FAQsActivity::class.java)
                startActivity(intent)
            }

            R.id.location_menu -> {
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
            }

            R.id.logout_menu -> {
                //Handle Logout
                logoutUser()
            }

            R.id.contact_menu -> {
                //Handle Contact Us
                val intent = Intent(this, ContactUsActivity::class.java)
                startActivity(intent)
            }

            else -> {
                // Handle other cases or do nothing
            }
        }

        drawerLayout.closeDrawers() // Close the navigation drawer
        return true
    }

    private fun logoutUser() {
        val intent = Intent(this, StartActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }


}