package com.example.c14220135_roomdb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c14220135_roomdb.database.daftarBelanja
import com.example.c14220135_roomdb.database.daftarBelanjaDB


interface OnItemClickCallback {
    fun delData(dtBelanja: daftarBelanja)
}

class AdapterDaftar(private val daftarBelanja: MutableList<daftarBelanja>) : RecyclerView.Adapter<AdapterDaftar.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    fun isiData(daftar: List<daftarBelanja>) {
        daftarBelanja.clear()
        daftarBelanja.addAll(daftar)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recyclerview, parent, false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val daftar = daftarBelanja[position]
        holder._tvTanggal.text = daftar.tanggal
        holder._tvItemBarang.text = daftar.item
        holder._tvjumlahBarang.text = daftar.jumlah

        holder._btnEdit.setOnClickListener {
            val intent = Intent(it.context, TambahDaftar::class.java)
            intent.putExtra("id", daftar.id)
            intent.putExtra("addedit", 1)
            it.context.startActivity(intent)
        }

        holder._btnDelete.setOnClickListener {
            onItemClickCallback.delData(daftar)
        }
    }

    override fun getItemCount(): Int {
        return daftarBelanja.size
    }

    // ViewHolder class to hold item views
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val _tvItemBarang: TextView = itemView.findViewById(R.id.tvitem)
        val _tvjumlahBarang: TextView = itemView.findViewById(R.id.tvjumlah)
        val _tvTanggal: TextView = itemView.findViewById(R.id.tvtanggal)
        val _btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val _btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }
}
