package co.edu.udea.compumovil.gr09_20241.roberto.data

import android.os.Bundle
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateItem (
    val date: LocalDate,
    val formattedDate: String,
    val isSelected: Boolean
) {
    fun toBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE))  // Store date as ISO string
        bundle.putString("formattedDate", formattedDate)
        bundle.putBoolean("isSelected", isSelected)
        return bundle
    }

    companion object {
        fun fromBundle(bundle: Bundle): DateItem {
            val dateString = bundle.getString("date") ?: ""  // Handle potential null values
            val formattedDate = bundle.getString("formattedDate") ?: ""
            val isSelected = bundle.getBoolean("isSelected")

            return DateItem(
                LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE),
                formattedDate,
                isSelected
            )
        }
    }
}

fun generateDateItems(): List<DateItem>{
    val currentDate = LocalDate.now()
    val dates = mutableListOf<DateItem>()
    for (i in -15..15){
        val date = currentDate.plusDays(i.toLong())
        val formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        val isSelected = date == currentDate
        dates.add(DateItem(date, formattedDate, isSelected))
    }
    return dates
}


