package co.edu.udea.compumovil.gr09_20241.roberto.database.routine

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true)
    val routineId: Int = 0,
    val title: String,
    val description: String?,
    val frecuency: String,
    val sessionTime: Float
)
