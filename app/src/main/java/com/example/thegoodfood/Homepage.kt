package com.example.thegoodfood

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class Homepage : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        firebaseAuth=FirebaseAuth.getInstance()
        sharedPreferences=getSharedPreferences("com.example.thegoodfood",0)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.userhome,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout->{
               firebaseAuth.signOut()
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
        return true
    }
}
