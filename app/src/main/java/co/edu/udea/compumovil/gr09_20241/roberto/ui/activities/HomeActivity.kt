package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.ui.RobertoScreen
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
    scheduledItemViewModel: ScheduledItemViewModel,

    navController: NavHostController,
){
    val taskState by taskViewModel.state.collectAsState()
    val routineState by routineViewModel.state.collectAsState()
    val scheduledState by scheduledItemViewModel.state.collectAsState()

    val dateItems = generateDateItems()

    var isDropdownMenuExpanded by remember {
        mutableStateOf(false)
    }

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
        IconButton(onClick = { isDropdownMenuExpanded = !isDropdownMenuExpanded }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
        FloatingActionButton(
            onClick = { isDropdownMenuExpanded = !isDropdownMenuExpanded },
            modifier = Modifier
                .padding(16.dp) // Add padding around FAB
                .align(Alignment.BottomEnd) // Position FAB bottom-right
        ) {
            Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add Clothing")
        }
        DropdownMenu(
            expanded = isDropdownMenuExpanded,
            onDismissRequest = { isDropdownMenuExpanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.new_task)) },
                onClick = {
                    isDropdownMenuExpanded = false
                    navController.navigate(RobertoScreen.NewTask.name)
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.new_routine)) },
                onClick = {
                    isDropdownMenuExpanded = false
                    navController.navigate(RobertoScreen.NewRoutine.name)
                }
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.new_goal)) },
                onClick = {
                    isDropdownMenuExpanded = false
                    navController.navigate(RobertoScreen.NewGoal.name)
                }
            )
        }
    }
}
