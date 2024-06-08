package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import co.edu.udea.compumovil.gr09_20241.roberto.data.generateDateItems

import androidx.compose.material3.MaterialTheme

import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.DateCarousel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel

import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.*

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel,
    routineViewModel: RoutineViewModel
){
    val taskState by taskViewModel.state.collectAsState()
    val routineState by routineViewModel.state.collectAsState()
    val dateItems = generateDateItems()

    Column {
        DateCarousel(dateItems = dateItems)

        taskState.tasks.forEach { task ->
            ScheduledCard(
                title = task.title,
                type = "Task",
                time = task.estimatedTime.toString(),
                backgroundColor = MaterialTheme.colorScheme.primary
            )
        }

        routineState.routines.forEach { routine ->
            ScheduledCard(
                title = routine.title,
                type = "Routine",
                time = routine.sessionTime.toString(),
                backgroundColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
