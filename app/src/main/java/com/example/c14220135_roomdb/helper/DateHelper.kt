package com.example.c14220135_roomdb.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateHelper {
    fun getCurrentDate():String{
        val dateFormat = SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss",
            Locale.getDefault()
        )
        val date = Date()
        return  dateFormat.format(date)
    }
}