package com.ensar.mobilprojem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DukkanAdapter(private val dukkanlist:ArrayList<Dukkan>)

    :RecyclerView.Adapter<DukkanAdapter.DukkanViewHolder>(){

    var onItemClick: ((Dukkan)->Unit)?=null

    class DukkanViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val imageView:ImageView=itemView.findViewById(R.id.imageView)
        val textView:TextView=itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DukkanViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return DukkanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dukkanlist.size
    }

    override fun onBindViewHolder(holder: DukkanViewHolder, position: Int) {
        val dukkan=dukkanlist[position]
        holder.imageView.setImageResource(dukkan.image)
        holder.textView.text=dukkan.name

        holder.itemView.setOnClickListener{

            onItemClick?.invoke(dukkan)
        }

    }
}