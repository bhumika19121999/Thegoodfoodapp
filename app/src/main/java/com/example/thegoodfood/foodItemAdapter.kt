package com.example.thegoodfood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class foodItemAdapter(var footlist:ArrayList<fooditemModel>,var context:Context):RecyclerView.Adapter<itemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.fooditemscard,parent,false)
        return itemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return footlist.size

    }
    fun update(fl:ArrayList<fooditemModel>){
        this.footlist=fl
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.name.setText(footlist[position].name)
        holder.price.setText(footlist[position].price)
        Glide.with(context).load(footlist[position].imgurl).into(holder.imgitem)
    }
}
class itemViewHolder(var itemview:View):RecyclerView.ViewHolder(itemview){
    var name=itemview.findViewById(R.id.recitemname) as TextView
    var price=itemview.findViewById(R.id.recitemprice) as TextView
    var imgitem=itemview.findViewById(R.id.recitemimage) as ImageView

}