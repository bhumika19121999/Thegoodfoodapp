package com.example.thegoodfood

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_admin_login.*
import kotlinx.android.synthetic.main.activity_main.*

class AdminLogin : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var databaseRef: DatabaseReference
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        firebaseAuth=FirebaseAuth.getInstance()
        databaseRef= FirebaseDatabase.getInstance().reference
        sharedPreferences=getSharedPreferences("com.example.thegoodfood",0)
        admin_btn.setOnClickListener {
            adminlogin()
        }
    }
    fun adminlogin(){
        val email=admin_email.text.toString().trim()
        val pass=admin_password.text.toString().trim()
        if(email.isNotEmpty() && pass.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener {
                    sharedPreferences.edit().putBoolean("isadmin",true).apply()
                    val i = Intent(this, AdminHome::class.java)
                    Log.d("key","inserted")
                    startActivity(i)
                    finish()


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
