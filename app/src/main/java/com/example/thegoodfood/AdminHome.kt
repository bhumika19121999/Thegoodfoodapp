package com.example.thegoodfood

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_home.*

class AdminHome : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
        firebaseAuth=FirebaseAuth.getInstance()
        sharedPreferences=getSharedPreferences("com.example.thegoodfood",0)
        addm.setOnClickListener {
            startActivity(Intent(this,addMenuItem::class.java))
        }
        showitems.setOnClickListener {
            startActivity(Intent(this,displayMenuItems::class.java))
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.userhome,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sharedPreferences.edit().putBoolean("isadmin", false).apply()
                firebaseAuth.signOut()
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
        return true
    }

}
