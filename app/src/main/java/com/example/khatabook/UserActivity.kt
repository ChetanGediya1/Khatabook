package com.example.khatabook

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.khatabook.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    lateinit var bind : ActivityUserBinding
 var hello : String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        bind = ActivityUserBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val tool = findViewById<Toolbar>(R.id.tool)
        val ti = findViewById<TextView>(R.id.tooltitle)
        ti.setText("Add Payment")
        setSupportActionBar(tool)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        bind.call3.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,hello.toString())
            intent.type = "text/plain"
            intent = Intent.createChooser(intent, "Share via")
           startActivity(intent)
        }


    }
}