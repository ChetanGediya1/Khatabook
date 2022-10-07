package com.example.khatabook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.khatabook.databinding.ActivitySecondBinding

class Second_Activity : AppCompatActivity() {
    lateinit var binding:ActivitySecondBinding
    lateinit var dbhelper: Database
    lateinit var Mrecycle :RecyclerView
    var list = arrayListOf<StatusModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        val mTitle = findViewById<TextView>(R.id.toolbar_title2)
        setSupportActionBar(toolbar)
        mTitle.setText("Add Customer");
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        var edtName = findViewById<EditText>(R.id.edt_name)
        var edtmobile = findViewById<EditText>(R.id.edt_mobile)
        var edtbill = findViewById<EditText>(R.id.edt_bill)
        var btnSubmit = findViewById<Button>(R.id.btnSubmit)
        dbhelper = Database(this)
        binding.left.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnCancel.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnSubmit.setOnClickListener {

            dbhelper.insertdata(
                edtName.text.toString(),
                edtmobile.text.toString(),
                edtbill.text.toString()


            )

            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }

    }

    }
