package co.edu.udea.compumovil.gr09_20241.roberto.database.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task ORDER BY deadline ASC")
    fun getTasksOrderedByDeadline(): Flow<List<Task>>
}