package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.khatabook.*
import com.example.khatabook.adapter.KhataAdpter2
import com.example.khatabook.databinding.ActivityUserBinding
import com.example.khatabook.model.StatusModel2

class UserActivity : AppCompatActivity() {
    lateinit var bind: ActivityUserBinding
    var whatsapptext = "Dear Sir/Madam,\nYour Payment of money is \nat Pending at Click here\n:" +
            "Khata.pe/t/o1VwijXF3i?s=pr&m=w \nto view the deatails and make the payment." +
            "\n" + "if the link is not clickable,\nplease save this contect and\n try" +
            " again."
    lateinit var dbhelper2: Database
    private var sumofgive = 0
    private var sumgive2 = 0
     var id = ""


    var list2 = arrayListOf<StatusModel2>()
    var userId = ""

var Fname = ""
    var Fcall = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityUserBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val tool = findViewById<Toolbar>(R.id.tool)
        id = intent.getStringExtra("user") as String

        setSupportActionBar(tool)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)



        val ti = findViewById<TextView>(R.id.tooltitle)

        userId = intent.getStringExtra("user").toString()
        dbhelper2 = Database(this);
         Fname = intent.getStringExtra("a1").toString()
         Fcall = intent.getStringExtra("a2").toString()

        ti.setText(Fname)
        //payment

        bind.btnPayment.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.payment)
            dialog.show()
        }
        bind.logoButton.setOnClickListener {
            intent = Intent(this,NameUpdate::class.java)
            intent.putExtra("Fname", Fname)
            intent.putExtra("Fcall", Fcall)
            intent.putExtra("id",id)
            intent.putExtra("userId",userId)

            startActivity(intent)
        }

        bind.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:+91$Fcall")
           startActivity(intent)
        }
//whatsapp
        bind.btnShare.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "'Your Pending Money  ₹ $sumofgive'\n" + whatsapptext.toString()
            )
            intent.type = "text/plain"
            intent = Intent.createChooser(intent, "Share via")
            startActivity(intent)
        }
        //red
        bind.youGave.setOnClickListener {
            val intent = Intent(this@UserActivity, PaymentAdd::class.java)
            intent.putExtra("user", userId)
            intent.putExtra("key", "0")
            startActivity(intent)

        }
        //green
        bind.youGOT.setOnClickListener {
            val intent = Intent(this@UserActivity, PaymentAdd::class.java)
            intent.putExtra("user", userId)
            intent.putExtra("key", "1")
            startActivity(intent)

        }

        //back
        bind.left3.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTransactionsList(list2: ArrayList<StatusModel2>) {
        val statusAdpter = KhataAdpter2(this, list2,Fname,Fcall)
        val ln = LinearLayoutManager(this)
        bind.Mrecycle2.layoutManager = ln
        bind.Mrecycle2.adapter = statusAdpter
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        list2 = dbhelper2.readTransactions(userId)
        setTransactionsList(list2)
        sumofgive = 0
        sumgive2 = 0

        for (i in list2) {

            if (i.status.equals("0"))
            {
                sumofgive += i.money.toInt()

            } else if (i.status.equals("1"))
            {
                sumgive2 += i.money.toInt()

            }

        }
        bind.edtTotal.setText("₹ $sumofgive").toString()
        bind.edtTotal2.setText("₹ $sumgive2").toString()

    }
}