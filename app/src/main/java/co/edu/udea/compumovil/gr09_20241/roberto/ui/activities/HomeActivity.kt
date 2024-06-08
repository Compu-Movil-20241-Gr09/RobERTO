package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.DateCarousel
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.ScheduledCard
import co.edu.udea.compumovil.gr09_20241.roberto.utils.generateDateItems
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.ScheduledItemViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel,
    routineViewModel: RoutineViewModel,
    scheduledItemViewModel: ScheduledItemViewModel
){
    val taskState by taskViewModel.state.collectAsState()
    val routineState by routineViewModel.state.collectAsState()
    val scheduledState by scheduledItemViewModel.state.collectAsState()

    val dateItems = generateDateItems()


    // This will only run when tasks or routines change
    LaunchedEffect(taskState.tasks, routineState.routines) {
        scheduledItemViewModel.scheduleItems(
            dates = dateItems,
            tasks = taskState.tasks,
            routines = routineState.routines
        )
    }

    Column {
        DateCarousel(dateItems = dateItems)

        scheduledState.scheduledItems.forEach { scheduledItem ->
            ScheduledCard(
                title = scheduledItem.title,
                type = scheduledItem.type,
                time = "${scheduledItem.startTime} - ${scheduledItem.endTime}",
                backgroundColor = Color.Cyan,
            )
        }
    }
}
