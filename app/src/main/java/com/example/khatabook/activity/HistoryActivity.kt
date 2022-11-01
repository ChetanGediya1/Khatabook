package com.example.khatabook.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.Database
import com.example.khatabook.R
import com.example.khatabook.adapter.KhataAdpter2
import com.example.khatabook.databinding.ActivityHistoryBinding
import com.example.khatabook.model.StatusModel2

class HistoryActivity : AppCompatActivity() {
    lateinit var dbhelper7: Database
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
        mTitle.setText("History")
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        binding.left7.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun Rvsetup() {
        val statusAdpter = KhataAdpter2(this, list4)
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