package co.edu.udea.compumovil.gr09_20241.roberto.database.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity()
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0,
    val title: String,
    val description: String?,
    val deadline: LocalDate,
    val estimatedTime: Float
)
