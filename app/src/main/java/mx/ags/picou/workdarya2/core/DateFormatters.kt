package mx.ags.picou.workdarya2.core

import android.icu.text.SimpleDateFormat
import java.util.*
import java.util.Locale.getDefault

class DateFormatters {
    companion object{
        val monthFormatter = SimpleDateFormat("MMMM yyyy", getDefault())
        val dayFormatter = SimpleDateFormat("dd-MM-yyyy", getDefault())
        val weekDayFormatter = SimpleDateFormat("EEE dd", getDefault())
    }
}