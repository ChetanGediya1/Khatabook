package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.khatabook.Database
import com.example.khatabook.R
import com.example.khatabook.databinding.ActivityPaymentAddBinding
import com.google.android.material.textfield.TextInputEditText

class PaymentAdd : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    lateinit var binding: ActivityPaymentAddBinding
    lateinit var dbhelper2: Database
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0
    private lateinit var edt_cus: TextInputEditText
    private lateinit var edt_ama: TextInputEditText
    private lateinit var edt_date: TextInputEditText
    private lateinit var edt_time: TextInputEditText

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0
    var saveHour = 0
    var saveMinute = 0
    var userId = ""


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pickDate()
        edt_cus = findViewById(R.id.edt_cus)
        edt_ama = findViewById(R.id.edt_ama)
        edt_date = findViewById(R.id.edt_date)
        edt_time = findViewById(R.id.edt_time)


        val toolbar = findViewById<Toolbar>(R.id.toolbar4)
        val mTitle = findViewById<TextView>(R.id.toolbartitle2)
        setSupportActionBar(toolbar)
        mTitle.setText("Add Payment")
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        val key1 = getIntent().getStringExtra("key");

        userId = intent.getStringExtra("user").toString()

        dbhelper2 = Database(this)

        binding.btnAddAmout.setOnClickListener {
            if (edt_ama.text!!.isEmpty()) {
                edt_ama.setError("Please Enter the Amout")

            } else
            {
                dbhelper2.insertTransactions(
                    userId,
                    edt_cus.text.toString().trim(),
                    edt_ama.text.toString().trim(),
                    edt_date.text.toString().trim(), key1!!.toInt(),
                    edt_time.text.toString().trim()
                )
                finish()
            }


        }
        binding.left2.setOnClickListener {
            intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
    @SuppressLint("InlinedApi")
    private fun getDateTimeCalender() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }

    @SuppressLint("NewApi")
    private fun pickDate() {
        binding.edtDate.setOnClickListener {
            getDateTimeCalender()

            DatePickerDialog(this, this, year, month, day).show()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month + 1
        saveYear = year

        TimePickerDialog(this, this, hour, minute, true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        saveHour = hourOfDay
        saveMinute = minute

        binding.edtDate.setText("$saveDay-$saveMonth-$saveYear").toString()
        binding.edtTime.setText("$saveHour:$saveMinute")
    }

}