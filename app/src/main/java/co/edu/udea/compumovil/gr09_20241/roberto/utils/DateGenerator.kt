package co.edu.udea.compumovil.gr09_20241.roberto.utils

import co.edu.udea.compumovil.gr09_20241.roberto.data.DateItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun generateDateItems(): List<DateItem> {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val today = LocalDate.now()
    return List(7) {
        val date = today.plusDays(it.toLong())
        DateItem(date, date.format(formatter), isSelected = true)
    }
}
