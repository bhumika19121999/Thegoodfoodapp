package com.example.thegoodfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {
    lateinit var firebaseAuth:FirebaseAuth
    lateinit var databaseRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth=FirebaseAuth.getInstance()
        databaseRef= FirebaseDatabase.getInstance().reference
        reg_signup.setOnClickListener {

            //database connectivity code
            sign()


        }
    }
    fun sign(){
        val email=signup_email.text.toString().trim()
        val pass=signup_password.text.toString().trim()
        val cpass=confirm_pass.text.toString().trim()
        if(email.isNotEmpty() &&
            pass.isNotEmpty() &&
            cpass.isNotEmpty() &&
            signup_add.text.toString().trim().isNotEmpty() &&
            signup_mobile.text.toString().trim().isNotEmpty() &&
            signup_name.text.toString().trim().isNotEmpty()) {

            if (pass == cpass) {
                var add = signup_add.text.toString().trim()
                var ph = signup_mobile.text.toString().trim()
                var name = signup_name.text.toString().trim()
                var hasmap = hashMapOf<String, String>()
                hasmap["email"] = email
                hasmap["address"] = add
                hasmap["name"] = name
                hasmap["ph"] = ph
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener {
                    databaseRef.child("users").child(it.user?.uid!!.toString()).setValue(hasmap)
                        .addOnSuccessListener {
                            startActivity(Intent(applicationContext, Homepage::class.java))
                            finish()
                        }
                }.addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        "Please check your credentials and Try Again!!",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            } else {

                Toast.makeText(applicationContext, "Pass should match ", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(applicationContext, "Please fil the details", Toast.LENGTH_SHORT).show()

        }
    }

}
