package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.khatabook.R

class splash : AppCompatActivity() {
    lateinit var icon :ImageView
    lateinit var text :TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        icon = findViewById(R.id.icon)
        text = findViewById(R.id.text)
        val slideanimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        icon.startAnimation(slideanimation)
        text.startAnimation(slideanimation)
        Handler().postDelayed({
            val intent = Intent(this@splash, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}