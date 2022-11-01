package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.Database
import com.example.khatabook.R
import com.example.khatabook.adapter.KhataAdpter
import com.example.khatabook.adapter.KhataAdpter2
import com.example.khatabook.databinding.ActivityPendingMoneyBinding
import com.example.khatabook.model.StatusModel
import com.example.khatabook.model.StatusModel2

class Pending_money : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var binding : ActivityPendingMoneyBinding
    lateinit var dbhelper3: Database
    var list3 = arrayListOf<StatusModel2>()
    var day = 0
    var month = 0
    var year = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pickDate()

        dbhelper3 = Database(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar5)
        val mTitle = findViewById<TextView>(R.id.toolbar_title5)
        setSupportActionBar(toolbar)
        mTitle.setText("Check Pending Money")
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        //back
        binding.left5.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun getDateTimeCalender(){
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)+0
        year= cal.get(Calendar.YEAR)

    }
    private fun pickDate(){
        binding.btnDate.setOnClickListener {
            getDateTimeCalender()

            DatePickerDialog(this,this,year,month,day).show()

        }
    }

    fun Rvsetup() {
        val statusAdpter = KhataAdpter2(this, list3)
        val ln = LinearLayoutManager(this)
        binding.Mrecycle3.layoutManager = ln
        binding.Mrecycle3.adapter = statusAdpter
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        day = dayOfMonth
        this.month = month+0
       this.year = year

        list3 = dbhelper3.readFilter(dat="$day-${month+1}-$year")
        Rvsetup()

    }
   override fun onResume() {
        super.onResume()
        getDateTimeCalender()
        Toast.makeText(this, "$day-${month+1}-$year", Toast.LENGTH_SHORT).show()

        list3 = dbhelper3.readFilter(dat="$day-${month+1}-$year")
        Rvsetup()
    }

}


