package co.edu.udea.compumovil.gr09_20241.roberto.database.routine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine")
data class Routine(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "routine_id")
    val routineId: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "frecuency")
    val frecuency: String,
    @ColumnInfo(name = "session_time")
    val sessionTime: Float
)
