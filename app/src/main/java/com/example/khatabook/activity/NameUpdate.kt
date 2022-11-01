package com.example.khatabook.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.khatabook.Database
import com.example.khatabook.R

import com.example.khatabook.databinding.ActivityNameUpdateBinding
import com.google.android.material.textfield.TextInputEditText

class NameUpdate : AppCompatActivity() {
    var fname=""
    var fcall=""
    var id = ""

    private lateinit var bindings: ActivityNameUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityNameUpdateBinding.inflate(layoutInflater)
        setContentView(bindings.root)
         fname = intent.getStringExtra("Fname").toString()
        fcall = intent.getStringExtra("Fcall").toString()
        id = intent.getStringExtra("id").toString()

        bindings.left9.setOnClickListener {
            finish()
        }

        bindings.edtnames.text = fname
        bindings.edtcalls.text = fcall

        bindings.btnnameEdit.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)

            val inflater = this.layoutInflater
            val dialogView: View = inflater.inflate(R.layout.editnamedialog, null)
            dialogBuilder.setView(dialogView)

            val edt_name2 = dialogView.findViewById<TextInputEditText>(R.id.edt_name2)
            val edt_mobile2 = dialogView.findViewById<TextInputEditText>(R.id.edt_mobile2)
            val btnSubmit2 = dialogView.findViewById<Button>(R.id.btnSubmit2)
            val btnCancel2 = dialogView.findViewById<TextView>(R.id.btnCancel2)


            edt_name2.setText(fname)
            edt_mobile2.setText(fcall)
            btnSubmit2.setOnClickListener {
                val dbhelper = Database(this)
                dbhelper.updatename(
                    id,
                    edt_name2.text.toString(),
                    edt_mobile2.text.toString(),
                )
               val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            btnCancel2.setOnClickListener{

                AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Are you sure delete this Information")
                    .setIcon(R.drawable.ic_baseline_delete_24)
                    .setPositiveButton(
                        "Yes")
                    { dialog, which ->
                        val dbhelper = Database(this)
                        dbhelper.Deletename(
                            id,
                            edt_name2.text.toString(),
                            edt_mobile2.text.toString()
                        )
                        intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)                    }
                                  .setNegativeButton(
                        "No"
                    ) { dialog, which ->
                        dialog.dismiss()

                    }
                    .create()
                    .show()



            }


            val alertDialog = dialogBuilder.create()
            alertDialog.show()

        }

    }
}