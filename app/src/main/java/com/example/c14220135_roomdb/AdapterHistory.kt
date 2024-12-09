package com.example.c14220135_roomdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c14220135_roomdb.database.historyBarang


class AdapterHistory(private val historyBarang: MutableList<historyBarang>) : RecyclerView.Adapter<AdapterHistory.ListViewHolder>() {


    fun isiData(daftar: List<historyBarang>) {
        historyBarang.clear()
        historyBarang.addAll(daftar)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recyclerviewhistory, parent, false
        )
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val daftar = historyBarang[position]
        holder._tvTanggal.text = daftar.tanggal
        holder._tvItemBarang.text = daftar.item
        holder._tvjumlahBarang.text = daftar.jumlah    }

    override fun getItemCount(): Int {
        return historyBarang.size
    }

    // ViewHolder class to hold item views
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val _tvItemBarang: TextView = itemView.findViewById(R.id.tvitem)
        val _tvjumlahBarang: TextView = itemView.findViewById(R.id.tvjumlah)
        val _tvTanggal: TextView = itemView.findViewById(R.id.tvtanggal)
    }
}
