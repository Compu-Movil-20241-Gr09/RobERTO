package co.edu.udea.compumovil.gr09_20241.roberto.utils

import co.edu.udea.compumovil.gr09_20241.roberto.data.DateItem
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.Routine
import co.edu.udea.compumovil.gr09_20241.roberto.database.scheduledItem.ScheduledItem
import co.edu.udea.compumovil.gr09_20241.roberto.database.scheduledItem.ScheduledItemDAO
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.Task
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun scheduleItemsUtil(
    dates: List<DateItem>,
    tasks: List<Task>,
    routines: List<Routine>,
    scheduledItemDao: ScheduledItemDAO
) = runBlocking {
    val scheduledItems = mutableListOf<ScheduledItem>()
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    fun parseDate(dateString: String): LocalDate? {
        return try {
            LocalDate.parse(dateString, dateFormatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    // Helper function to find the next available time slot
    fun findNextAvailableTimeSlot(date: String, startTime: LocalTime): LocalTime? {
        val endTime = LocalTime.of(18, 0)
        var currentStartTime = startTime

        while (currentStartTime.isBefore(endTime)) {
            val overlapping = scheduledItems.any {
                it.date == date &&
                        LocalTime.parse(it.startTime, timeFormatter).isBefore(currentStartTime.plusHours(2)) &&
                        LocalTime.parse(it.endTime, timeFormatter).isAfter(currentStartTime)
            }
            if (!overlapping) {
                return currentStartTime
            }
            currentStartTime = currentStartTime.plusMinutes(30)
        }
        return null
    }

    // Schedule routines
    for (routine in routines) {
        for (dateItem in dates) {
            val parsedDate = parseDate(dateItem.formattedDate)
            if (dateItem.isSelected && parsedDate != null && routine.frequency.contains(parsedDate.dayOfWeek.toString())) {
                val startTime = LocalTime.of(8, 0)
                scheduledItems.add(
                    ScheduledItem(
                        date = dateItem.formattedDate,
                        startTime = startTime.format(timeFormatter),
                        endTime = startTime.plusHours(1).format(timeFormatter), // Assuming 1 hour for routine
                        title = routine.title,
                        type = "Routine"
                    )
                )
            }
        }
    }

    // Sort tasks by deadline
    val sortedTasks = tasks.sortedBy {
        it.deadline?.let { deadline -> LocalDate.parse(deadline, dateFormatter) }
    }

    // Schedule tasks
    for (task in sortedTasks) {
        var timeNeeded = task.estimatedTime
        for (dateItem in dates) {
            if (timeNeeded <= 0) break
            if (dateItem.isSelected) {
                var startTime = LocalTime.of(8, 0)
                while (timeNeeded > 0 && startTime.isBefore(LocalTime.of(18, 0))) {
                    val nextSlot = findNextAvailableTimeSlot(dateItem.formattedDate, startTime)
                    if (nextSlot != null) {
                        val duration = minOf(2.0f, timeNeeded)
                        scheduledItems.add(
                            ScheduledItem(
                                date = dateItem.formattedDate,
                                startTime = nextSlot.format(timeFormatter),
                                endTime = nextSlot.plusMinutes((duration * 60).toLong()).format(timeFormatter),
                                title = task.title,
                                type = "Task"
                            )
                        )
                        timeNeeded -= duration
                        startTime = nextSlot.plusMinutes((duration * 60).toLong())
                    } else {
                        break
                    }
                }
            }
        }
    }

    // Clear existing scheduled items and insert new ones
    scheduledItemDao.deleteAllScheduledItems()
    scheduledItemDao.insertAllScheduledItems(scheduledItems)
}