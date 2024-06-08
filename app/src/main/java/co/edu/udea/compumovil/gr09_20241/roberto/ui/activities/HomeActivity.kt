package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel,
    routineViewModel: RoutineViewModel,
    scheduledItemViewModel: ScheduledItemViewModel,

    navController: NavHostController,
) {
    val taskState by taskViewModel.state.collectAsState()
    val routineState by routineViewModel.state.collectAsState()
    val scheduledState by scheduledItemViewModel.state.collectAsState()

    val dateItems = generateDateItems()

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
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

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            DateCarousel(dateItems = dateItems)
            Row (
                modifier = Modifier
            ) {
                Spacer(modifier = Modifier.padding(40.dp))
                // FloatingActionButton for List
                FloatingActionButton(
                    onClick = { navController.navigate(RobertoScreen.ListItems.name) },
                    modifier = Modifier.padding(16.dp) // Add padding around FAB
                    // Position FAB bottom-right
                ) {
                    Icon(Icons.AutoMirrored.Filled.List, contentDescription = "List")
                }

                Spacer(modifier = Modifier.padding(25.dp))

                // FloatingActionButton for Add
                FloatingActionButton(
                    onClick = { isSheetOpen = true },
                    modifier = Modifier
                        .padding(16.dp) // Add padding around FAB // Position FAB bottom-right
                ) {
                    Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add item")
                }
                if (isSheetOpen) {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = { isSheetOpen = false }
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 4.dp),
                            onClick = {
                                navController.navigate(RobertoScreen.NewTask.name)
                                isSheetOpen = false
                            }
                        ) {
                            Text(text = stringResource(R.string.new_task))
                        }
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 4.dp),
                            onClick = {
                                navController.navigate(RobertoScreen.NewRoutine.name)
                                isSheetOpen = false
                            }
                        ) {
                            Text(text = stringResource(R.string.new_routine))
                        }
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 4.dp),
                            onClick = {
                                navController.navigate(RobertoScreen.NewGoal.name)
                                isSheetOpen = false
                            }
                        ) {
                            Text(text = stringResource(R.string.new_goal))
                        }
                    }
                }
            } // End Row
            scheduledState.scheduledItems.forEach { scheduledItem ->
                ScheduledCard(
                    title = scheduledItem.title,
                    type = scheduledItem.type,
                    time = "${scheduledItem.startTime} - ${scheduledItem.endTime}",
                    backgroundColor = if (scheduledItem.type == "Task") MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary,
                )
            }
        }
    }
}
