package com.example.khatabook

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.databinding.ActivityMainBinding
import com.example.khatabook.databinding.ActivitySecondBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bind :ActivitySecondBinding


    lateinit var dbhelper: Database
    var list = arrayListOf<StatusModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind = ActivitySecondBinding.inflate(layoutInflater)

        val toolbar = findViewById<Toolbar>(R.id.toolbar1)
        val mTitle = findViewById<TextView>(R.id.toolbar_title1)
        setSupportActionBar(toolbar)
        mTitle.setText(toolbar.getTitle());
     getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        dbhelper = Database(this)

        binding.h.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }


        binding.addButton.setOnClickListener {
            intent = Intent(this@MainActivity,Second_Activity::class.java)
            startActivity(intent)

        }


    }



    fun Rvsetup() {
        var statusAdpter = KhataAdpter(this, list)
        var ln = LinearLayoutManager(this)
        binding.Mrecycle.layoutManager = ln
        binding.Mrecycle.adapter = statusAdpter
    }

    override fun onStart() {
        super.onStart()
        list = dbhelper.readData()
        Rvsetup()
    }
}