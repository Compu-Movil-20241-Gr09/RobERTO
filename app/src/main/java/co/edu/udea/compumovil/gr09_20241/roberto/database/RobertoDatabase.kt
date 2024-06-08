package co.edu.udea.compumovil.gr09_20241.roberto.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.Goal
import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.GoalDAO
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.Routine
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.RoutineDAO
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.Task
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.TaskDAO
import co.edu.udea.compumovil.gr09_20241.roberto.database.user.User
import co.edu.udea.compumovil.gr09_20241.roberto.database.user.UserDAO

@Database(
    entities = [
        Task::class,
        Routine::class,
        Goal::class,
        User::class,
    ],
    version = 1
)
abstract class RobertoDatabase: RoomDatabase() {
    abstract val taskDao: TaskDAO
    abstract val routineDao: RoutineDAO
    abstract val goalDao: GoalDAO
    abstract val userDao: UserDAO
}