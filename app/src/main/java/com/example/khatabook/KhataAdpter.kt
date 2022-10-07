package com.example.khatabook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KhataAdpter(val mainActivity: MainActivity, val list: ArrayList<StatusModel>)
    : RecyclerView.Adapter<KhataAdpter.viewData>()
{

    class viewData(itemView: View) :RecyclerView.ViewHolder(itemView){
        var Sname = itemView.findViewById<TextView>(R.id.Sname)
        var SMobile = itemView.findViewById<TextView>(R.id.Smobile)
        var Sbill= itemView.findViewById<TextView>(R.id.Sbill)
        var user = itemView.findViewById<RelativeLayout>(R.id.user)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewData {
        var view = LayoutInflater.from(mainActivity).inflate(R.layout.list_item,parent,false)
        return viewData(view)
    }

    override fun onBindViewHolder(holder: viewData, position: Int) {
        holder.Sname.text = list[position].name
        holder.SMobile.text = list[position].mobile
        holder.Sbill.text = list[position].bill
        holder.user.setOnClickListener {
            var intent = Intent(mainActivity,UserActivity::class.java)
            mainActivity.startActivity(intent)
        }





    }

    override fun getItemCount(): Int {
        return list.size
    }
}