package co.edu.udea.compumovil.gr09_20241.roberto.database.routine

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDAO {
    @Upsert
    suspend fun upsertRoutine(routine: Routine)

    @Delete
    suspend fun deleteRoutine(routine: Routine)

    @Query("SELECT * FROM routine")
    fun getAllRoutines(): Flow<List<Routine>>
}