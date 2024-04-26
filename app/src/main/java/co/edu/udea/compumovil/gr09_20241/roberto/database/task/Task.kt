package co.edu.udea.compumovil.gr09_20241.roberto.database.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    val taskId: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "deadline")
    val deadline: String? = null,
    @ColumnInfo(name = "estimated_time")
    val estimatedTime: Float
)
