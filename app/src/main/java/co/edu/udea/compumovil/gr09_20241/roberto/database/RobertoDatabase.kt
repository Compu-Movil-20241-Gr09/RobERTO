package co.edu.udea.compumovil.gr09_20241.roberto.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.Goal
import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.GoalDAO
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.Routine
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.RoutineDAO
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.Task
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.TaskDAO

@Database(
    entities = [
        Task::class,
        Routine::class,
        Goal::class
    ],
    version = 1
)
abstract class RobertoDatabase: RoomDatabase() {
    abstract val taskDao: TaskDAO
    abstract val routineDao: RoutineDAO
    abstract val goalDao: GoalDAO
}