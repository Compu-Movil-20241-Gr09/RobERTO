package co.edu.udea.compumovil.gr09_20241.roberto.database.scheduledItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheduled_item")
data class ScheduledItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "scheduled_item_id")
    val id: Int = 0,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "start_time")
    val startTime: String,
    @ColumnInfo(name = "end_time")
    val endTime: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String
)
