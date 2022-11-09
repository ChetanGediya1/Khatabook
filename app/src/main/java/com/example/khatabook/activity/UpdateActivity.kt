package com.example.khatabook.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.khatabook.Database
import com.example.khatabook.R
import com.example.khatabook.databinding.ActivityUpdateBinding
import com.example.khatabook.model.StatusModel2
import com.google.android.material.textfield.TextInputEditText


class UpdateActivity : AppCompatActivity() {
    lateinit var bindings: ActivityUpdateBinding
    var list2 = arrayListOf<StatusModel2>()
    var id = ""
    var userId = ""

    var Customer = ""
    var Date = ""
    var Time = ""
    var Money0 = ""
var Fname = ""
    var Fcall = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(bindings.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar8)
        val mTitle = findViewById<TextView>(R.id.toolbar_title8)
        setSupportActionBar(toolbar)
        mTitle.setText("Entry Deatails")
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        id = intent.getStringExtra("id") as String
        userId = intent.getStringExtra("userId") as String

        Customer = intent.getStringExtra("c1") as String
        Date = intent.getStringExtra("c2") as String
        Time = intent.getStringExtra("c3") as String
        Fname = intent.getStringExtra("Fname") as String
        Fcall = intent.getStringExtra("Fcall") as String


        Money0 = intent.getStringExtra("c4") as String

        bindings.Sdate.setText("$Date - $Time")
        bindings.edtDeatails.setText("$Customer")
        bindings.edtTotal2.setText("₹ $Money0")
        bindings.SRemak.setText("$Fname")

        bindings.edtTotal.setText("₹ $Money0")
        bindings.btnEdit.setOnClickListener {
            updatadialog()
        }
        bindings.btnCalling.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:+91$Fcall")
            startActivity(intent)
        }

        bindings.left8.setOnClickListener {
            finish()
        }
    }

    private fun updatadialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.editdialog, null)
        dialogBuilder.setView(dialogView)

        val edt_cus1 = dialogView.findViewById<TextInputEditText>(R.id.edt_cus1)
        val edt_ama1 = dialogView.findViewById<TextInputEditText>(R.id.edt_ama1)
        val edt_date1 = dialogView.findViewById<TextInputEditText>(R.id.edt_date1)
        val edt_time1 = dialogView.findViewById<TextInputEditText>(R.id.edt_time1)
        val btn_add1 = dialogView.findViewById<Button>(R.id.btn_add_amout1)
        val btn_delete = dialogView.findViewById<Button>(R.id.btnDelete)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()


        edt_cus1.setText(Customer)
        edt_ama1.setText(Money0)
        edt_date1.setText(Date)
        edt_time1.setText(Time)

        btn_add1.setOnClickListener {
            val dbhelper = Database(this)
            dbhelper.updatedata(
                edt_cus1.text.toString(),
                edt_ama1.text.toString(),
                edt_date1.text.toString(),
                edt_time1.text.toString(),
                id,
                userId
            )
            alertDialog.dismiss()
            finish()
        }

        btn_delete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure delete this Information")
                .setIcon(R.drawable.ic_baseline_delete_24)
                .setPositiveButton(
                    "Yes")
                { dialog, which ->
                    val dbhelper = Database(this)
                    dbhelper.DeleteData(
                        edt_cus1.text.toString(),
                        edt_ama1.text.toString(),
                        edt_date1.text.toString(),
                        edt_time1.text.toString(),
                        id,
                        userId
                    )
                    alertDialog.dismiss()
                    finish()
                }
                .setNegativeButton(
                    "No"
                ) { dialog, which ->
                    dialog.dismiss()

                }
                .create()
                .show()

//dialog

        }

    }


}


