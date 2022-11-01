package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.khatabook.Database
import com.example.khatabook.R
import com.example.khatabook.databinding.ActivityMainBinding
import com.example.khatabook.model.StatusModel
import com.example.khatabook.databinding.ActivitySecondBinding

class Second_Activity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    lateinit var dbhelper: Database

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        val mTitle = findViewById<TextView>(R.id.toolbar_title2)
        setSupportActionBar(toolbar)
        mTitle.text = "Add Customer"
        supportActionBar?.setDisplayShowTitleEnabled(false);

        val edtName = findViewById<EditText>(R.id.edt_name)
        val edtmobile = findViewById<EditText>(R.id.edt_mobile)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        dbhelper = Database(this)
        binding.left.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnSubmit.setOnClickListener {
            dbhelper.addCustomer(
                edtName.text.toString().trim(),
                edtmobile.text.toString().trim()
                )
          finish()


        }

    }

}
