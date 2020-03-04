package com.example.thegoodfood

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var databaseRef: DatabaseReference
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth=FirebaseAuth.getInstance()
        databaseRef= FirebaseDatabase.getInstance().reference
        sharedPreferences=getSharedPreferences("com.example.thegoodfood",0)
        checkExistingUser()
        login_btn.setOnClickListener() {

            //database connectivity code

            login()
        }

        login_signup.setOnClickListener {
            var i = Intent(this, Signup::class.java)
            startActivity(i)

        }

        login_skip.setOnClickListener {
            var i = Intent(this, AdminLogin::class.java)
            startActivity(i)
        }
    }
    fun checkExistingUser(){
        if(firebaseAuth.currentUser!=null) {
            if(sharedPreferences.getBoolean("isadmin",false)){
                var i = Intent(this, AdminHome::class.java)
                startActivity(i)
            }
            else{
                var i = Intent(this,Homepage::class.java)
                startActivity(i)
            }
        }
    }
    override fun onStart() {
        super.onStart()

    }
    fun login(){
        val email=login_email.text.toString().trim()
        val pass=login_password.text.toString().trim()
        if(email.isNotEmpty() && pass.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener {
                var i = Intent(this, Homepage::class.java)
                startActivity(i)

            }.addOnFailureListener{
                Toast.makeText(
                    applicationContext,
                    "Please check your credentials and Try Again!!",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
        else{
            Toast.makeText(applicationContext, "Please fil the details", Toast.LENGTH_SHORT).show()

        }
    }
}



