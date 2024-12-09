package com.example.c14220135_roomdb.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface historyBarangDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(daftar: historyBarang)

    @Query("UPDATE historyBarang SET tanggal = :isi_tanggal, item = :isi_item, jumlah = :isi_jumlah WHERE id = :pilihid")
    fun update(
        isi_tanggal: String,
        isi_item: String,
        isi_jumlah: String,
        pilihid: Int
    )

    @Delete
    fun delete(daftar: historyBarang)

    @Query("SELECT * FROM historyBarang ORDER BY id asc")
    fun selectAll(): MutableList<historyBarang>

    @Query ("SELECT * FROM historyBarang WHERE id=:isi_id")
    suspend fun getItem(isi_id: Int): historyBarang
}