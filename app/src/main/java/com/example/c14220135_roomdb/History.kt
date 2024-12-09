package com.example.c14220135_roomdb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c14220135_roomdb.database.historyBarang
import com.example.c14220135_roomdb.database.historyBarangDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class History : AppCompatActivity() {
    private lateinit var DB : historyBarangDB
    private lateinit var adapterDaftar: AdapterHistory
    private  var arDaftar : MutableList<historyBarang> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        DB  = historyBarangDB.getDatabase(this)
        adapterDaftar = AdapterHistory(arDaftar)
        var _rvHistory = findViewById<RecyclerView>(R.id.rvHistory)
        _rvHistory.layoutManager = LinearLayoutManager(this )
        _rvHistory.adapter = adapterDaftar
        
    }


    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).async {
            val daftarBelanja = DB.fundaftarHistoryDAO().selectAll()
            adapterDaftar.isiData(daftarBelanja)
            Log.d("data ROOM", daftarBelanja.toString())
        }
    }
}