package com.example.khatabook.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView

import androidx.recyclerview.widget.RecyclerView
import com.example.khatabook.R
import com.example.khatabook.activity.UpdateActivity
import com.example.khatabook.model.StatusModel2


class KhataAdpter2(
    var userActivity: Activity,
    val list2: ArrayList<StatusModel2>,
    val Fname: String,
    val Fcall: String
)
    : RecyclerView.Adapter<KhataAdpter2.viewData2>()
{
    class viewData2(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val scus = itemView.findViewById<TextView>(R.id.Scus)
        val sama = itemView.findViewById<TextView>(R.id.Sama)
        val sama2  = itemView.findViewById<TextView>(R.id.Sama2)
        val Sdate = itemView.findViewById<TextView>(R.id.Sdate)
        val Stime = itemView.findViewById<TextView>(R.id.Stime)
        val btnTranstion = itemView.findViewById<CardView>(R.id.btnTranstion)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewData2 {
        val view = LayoutInflater.from(userActivity).inflate(R.layout.list_item2,parent,false)
        return viewData2(view)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: viewData2, position: Int) {
         holder.scus.text = list2[position].cus
        holder.Sdate.text = list2[position].date
        holder.Stime.text = list2[position].time
        holder.btnTranstion.setOnClickListener {
            val statusModel = list2[holder.adapterPosition]
            val intent = Intent(userActivity, UpdateActivity::class.java)
            intent.putExtra("id",statusModel.id)
            intent.putExtra("userId",statusModel.userId)
            intent.putExtra("c1",statusModel.cus)
            intent.putExtra("c2",statusModel.date)
            intent.putExtra("c3",statusModel.time)
            intent.putExtra("Fname",Fname)
            intent.putExtra("Fcall",Fcall)
            if (list2[position].status.equals("0"))
            {
                intent.putExtra("c4",list2[position].money)
            }
            else
            {
                intent.putExtra("c4",list2[position].money)
            }
            userActivity.startActivity(intent)
        }
        if (list2[position].status.equals("0"))
        {
            holder.sama2.text = "₹ ${list2[position].money}"
        }
        else
        {
            holder.sama.text = "₹ "+list2[position].money
        }

    }

    override fun getItemCount(): Int {
    return list2.size
    }


}
