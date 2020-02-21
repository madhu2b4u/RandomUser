package com.demo.randomuser.common

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
}