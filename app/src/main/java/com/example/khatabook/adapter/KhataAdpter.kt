package com.example.khatabook.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.khatabook.R
import com.example.khatabook.activity.UserActivity
import com.example.khatabook.model.StatusModel

class KhataAdpter(private val mContext: Activity, private val list: ArrayList<StatusModel>)
    : RecyclerView.Adapter<KhataAdpter.viewData>()
{

    class viewData(itemView: View) :RecyclerView.ViewHolder(itemView){
        var Sname = itemView.findViewById<TextView>(R.id.Sname)
        var SMobile = itemView.findViewById<TextView>(R.id.Smobile)
        var user = itemView.findViewById<CardView>(R.id.user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return viewData(view)
    }

    override fun onBindViewHolder(holder: viewData, position: Int) {
        holder.Sname.text = list[position].name
        holder.SMobile.text = list[position].mobile
        holder.user.setOnClickListener {
            val statusModel = list[position]
            val intent = Intent(mContext, UserActivity::class.java)
            intent.putExtra("user",statusModel.id)
            intent.putExtra("a1",list[position].name)
            intent.putExtra("a2",list[position].mobile)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}