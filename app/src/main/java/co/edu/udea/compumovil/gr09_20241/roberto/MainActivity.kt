package co.edu.udea.compumovil.gr09_20241.roberto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import co.edu.udea.compumovil.gr09_20241.roberto.database.RobertoDatabase
import co.edu.udea.compumovil.gr09_20241.roberto.ui.RobertoApp
import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.HomeScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.NewRoutineScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.theme.RobERTOTheme
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.GoalViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.UserViewModel

class MainActivity : ComponentActivity() {

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            RobertoDatabase::class.java,
            "RobERTO_Database"
        ).build()
    }

    val taskViewModel by viewModels<TaskViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(db.taskDao) as T
                }
            }
        }
    )

    val routineViewModel by viewModels<RoutineViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return RoutineViewModel(db.routineDao) as T
                }
            }
        }
    )

    val goalViewModel by viewModels<GoalViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return GoalViewModel(db.goalDao) as T
                }
            }
        }
    )

    val userViewModel by viewModels<UserViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserViewModel(db.userDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RobERTOTheme {
                RobertoApp(
                    taskViewModel = taskViewModel,
                    routineViewModel = routineViewModel,
                    goalViewModel = goalViewModel,
                    userViewModel = userViewModel
                )
            }
        }
    }
}
