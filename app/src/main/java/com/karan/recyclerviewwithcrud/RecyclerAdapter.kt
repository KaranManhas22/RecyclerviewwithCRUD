package com.karan.recyclerviewwithcrud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class RecyclerAdapter(var item: ArrayList<Title>,private var recyclerInterface: RecyclerInterface):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)  {
        val title:TextView=view.findViewById(R.id.title)
        val description:TextView=view.findViewById(R.id.description)
        var btndel:Button=view.findViewById(R.id.btndelete)
        var btnupd:Button=view.findViewById(R.id.btnupdate)

    }
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         var view =
             LayoutInflater.from(parent.context).inflate(R.layout.recycler_adapter, parent, false)
         return ViewHolder(view)
     }

     override fun getItemCount(): Int {
         return item.size
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentItem=item[position]
         holder.title.text=currentItem.title
         holder.description.text=currentItem.description
         holder.btndel.setOnClickListener {
             recyclerInterface.Delete_data(position)
         }

     }


 }



