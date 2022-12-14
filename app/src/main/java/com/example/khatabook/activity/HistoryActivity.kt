package com.example.khatabook.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.Database
import com.example.khatabook.R
import com.example.khatabook.adapter.KhataAdpter2
import com.example.khatabook.databinding.ActivityHistoryBinding
import com.example.khatabook.model.StatusModel2

class HistoryActivity : AppCompatActivity() {
    lateinit var dbhelper7: Database
    var Fname = ""
    var Fcall = ""

    lateinit var binding: ActivityHistoryBinding
    var list4 = arrayListOf<StatusModel2>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbhelper7 = Database(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar7)
        val mTitle = findViewById<TextView>(R.id.toolbar_title7)
        setSupportActionBar(toolbar)
        mTitle.text = "History"
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
     //   Fname = intent.getStringExtra("a1").toString()

        binding.left7.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun Rvsetup() {
        val statusAdpter = KhataAdpter2(this, list4, Fname, Fcall)
        val ln = LinearLayoutManager(this)
        binding.Mrecycle7.layoutManager = ln
        binding.Mrecycle7.adapter = statusAdpter
    }

    override fun onResume() {
        super.onResume()
        list4 = dbhelper7.History()
        Rvsetup()
    }
}