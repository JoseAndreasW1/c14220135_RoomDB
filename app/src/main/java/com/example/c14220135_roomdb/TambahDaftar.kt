package com.example.c14220135_roomdb

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.c14220135_roomdb.database.daftarBelanja
import com.example.c14220135_roomdb.database.daftarBelanjaDB
import com.example.c14220135_roomdb.helper.DateHelper.getCurrentDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TambahDaftar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var DB = daftarBelanjaDB.getDatabase(this)
        var tanggal = getCurrentDate()
        val _etItem = findViewById<EditText>(R.id.etItem)
        val _etJumlah = findViewById<EditText>(R.id.etJumlah)
        val btnUpdate =  findViewById<Button>(R.id.btnUpdate)
        val btnTambah = findViewById<Button>(R.id.btnTambah)

        var iID : Int = 0
        var iAddEdit : Int = 0
        iID = intent.getIntExtra("id", 0)
        iAddEdit = intent.getIntExtra("addedit", 0)
        if (iAddEdit == 0){
            btnTambah.visibility = View.VISIBLE
            btnUpdate.visibility = View.GONE
            _etItem.isEnabled = true
        }else{
            btnTambah.visibility = View.GONE
            btnUpdate.visibility = View.VISIBLE
            _etItem.isEnabled = false
            CoroutineScope(Dispatchers.IO).async {
                val item = DB.fundaftarBelanjaDAO().getItem(iID)
                _etItem.setText(item.item)
                _etJumlah.setText(item.jumlah)
            }
        }
        btnTambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.fundaftarBelanjaDAO().insert(
                    daftarBelanja(
                        tanggal = tanggal,
                        item= _etItem.text.toString(),
                        jumlah = _etJumlah.text.toString()
                    )
                )
        }
            finish()
        }

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.fundaftarBelanjaDAO().update(
                   isi_tanggal = tanggal,
                    isi_item = _etItem.text.toString(),
                    isi_jumlah = _etJumlah.text.toString(),
                    pilihid = iID
                )
            }
            finish()
        }
    }
}
