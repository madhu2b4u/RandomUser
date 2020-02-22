package com.demo.randomuser.common

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Utils {

    fun formatDate(dateString: String): String {
        try {
            var sd = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
            val d = sd.parse(dateString)
            sd = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
            return sd.format(d)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}