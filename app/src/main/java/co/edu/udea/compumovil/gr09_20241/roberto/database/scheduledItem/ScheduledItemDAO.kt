package co.edu.udea.compumovil.gr09_20241.roberto.database.scheduledItem

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduledItemDAO {
    @Upsert
    suspend fun upsertScheduledItem(task: ScheduledItem)

    @Insert
    suspend fun insertAllScheduledItems(scheduledItems: List<ScheduledItem>)

    @Delete
    suspend fun deleteScheduledItem(task: ScheduledItem)

    @Query("DELETE FROM scheduled_item")
    suspend fun deleteAllScheduledItems()

    @Query("SELECT * FROM scheduled_item")
    fun getAllScheduledItems(): Flow<List<ScheduledItem>>
}