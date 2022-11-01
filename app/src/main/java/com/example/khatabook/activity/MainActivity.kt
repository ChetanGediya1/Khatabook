package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.Database
import com.example.khatabook.adapter.KhataAdpter
import com.example.khatabook.R
import com.example.khatabook.model.StatusModel
import com.example.khatabook.databinding.ActivityMainBinding
import com.example.khatabook.model.StatusModel2


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var list2 = arrayListOf<StatusModel2>()
    private lateinit var dbhelper: Database
   private var list = arrayListOf<StatusModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar1)
        val mTitle = findViewById<TextView>(R.id.toolbar_title1)
        setSupportActionBar(toolbar)
        mTitle.setText(toolbar.title)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        dbhelper = Database(this)
        binding.h.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.addButton.setOnClickListener {
            intent = Intent(this, Second_Activity::class.java)
            startActivity(intent)

        }
        binding.btnHistory.setOnClickListener {
           intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)

        }
        binding.help.setOnClickListener {
            intent = Intent(this, Pending_money::class.java)
            startActivity(intent)

        }

    }

    fun Rvsetup(list: ArrayList<StatusModel>) {
        val statusAdpter = KhataAdpter(this, list)
        val ln = LinearLayoutManager(this)
        binding.Mrecycle.layoutManager = ln
        binding.Mrecycle.adapter = statusAdpter
    }


    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        list = dbhelper.readData()
        list2 = dbhelper.readTotal()
        Rvsetup(list)
        var sumofgive = 0
        var sumgive2 = 0
        for (i in list2) {
            if (i.status.equals("0"))
            {
                sumofgive += i.money.toInt()

            } else if (i.status.equals("1"))
            {
                sumgive2 += i.money.toInt()

            }




        }
        binding.MoneyGive.setText("₹ $sumgive2").toString()

        binding.MoneyGet.setText("₹ $sumofgive").toString()
    }

    override fun onBackPressed() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.back)


        val yes = dialog.findViewById<Button>(R.id.yes)
        val no = dialog.findViewById<Button>(R.id.no)

        yes.setOnClickListener {
            this.finish()
            dialog.onBackPressed()
        }

        no.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }


}