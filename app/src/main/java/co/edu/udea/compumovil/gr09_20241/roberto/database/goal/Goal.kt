package co.edu.udea.compumovil.gr09_20241.roberto.database.goal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val goalId: Int = 0,
    val title: String,
    val description: String?,
    val steps: String?
)
