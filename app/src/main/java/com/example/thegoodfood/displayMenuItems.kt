package com.example.thegoodfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_display_menu_items.*

class displayMenuItems : AppCompatActivity() {
    lateinit var db:DatabaseReference
    lateinit var st:StorageReference
    lateinit var foodlist:ArrayList<fooditemModel>
    lateinit var foodItemAdapter: foodItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_menu_items)
        db=FirebaseDatabase.getInstance().reference
        st=FirebaseStorage.getInstance().reference
        foodlist= ArrayList()
        foodItemAdapter= foodItemAdapter(foodlist,applicationContext)
        fooditemrec.layoutManager=GridLayoutManager(applicationContext,2)
        fooditemrec.adapter=foodItemAdapter
        db.child("Admin").child("XAk5RJc3YROwvdEvFZ3RDfCfXoD2").addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d("data","got")
                for(ds in p0.children){
                    var fooditemModel=fooditemModel(
                        ds.child("url").value.toString(),
                        ds.child("name").value.toString(),
                        ds.child("price").value.toString(),
                        ds.child("desc").value.toString(),
                        ds.child("type").value.toString(),
                        ds.child("cat").value.toString()
                        )
                    foodlist.add(fooditemModel)
                }
                foodItemAdapter.update(foodlist)
                foodItemAdapter.notifyDataSetChanged()
            }

        })

    }
}
