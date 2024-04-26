package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.data.generateDateItems
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.GoalState
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.RoutineState
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.TaskState
import co.edu.udea.compumovil.gr09_20241.roberto.events.TaskEvent
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.DateCarousel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.GoalViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel,
    routineViewModel: RoutineViewModel,
    goalViewModel: GoalViewModel,
    onNewTaskSelected: () -> Unit,
    onNewRoutineSelected: () -> Unit,
    onNewGoalSelected: () -> Unit
){
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val taskState by taskViewModel.state.collectAsState()
    val routineState by routineViewModel.state.collectAsState()
    val goalState by goalViewModel.state.collectAsState()

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        HomePortrait(
            taskState,
            routineState,
            goalState,
            onNewTaskSelected,
            onNewRoutineSelected,
            onNewGoalSelected
        )
    }else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
        HomeLandscape(
            taskState,
            routineState,
            goalState,
            onNewTaskSelected,
            onNewRoutineSelected,
            onNewGoalSelected
        )
    }
}

@Composable
fun HomePortrait(
    taskState: TaskState,
    routineState: RoutineState,
    goalState: GoalState,
    onNewTaskSelected: () -> Unit,
    onNewRoutineSelected: () -> Unit,
    onNewGoalSelected: () -> Unit
){
    val dateItems = generateDateItems()

    Column {
        DateCarousel(dateItems = dateItems)

        Button(
            onClick = {
                onNewTaskSelected()
            }
        ) {
            Row {
                Text(text = stringResource(R.string.new_task))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = ""
                )
            }
        }
        Button(
            onClick = {
                onNewRoutineSelected()
            }
        ) {
            Row {
                Text(text = stringResource(R.string.new_routine))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = ""
                )
            }
        }
        Button(
                onClick = {
                    onNewGoalSelected()
                }
                ) {
            Row {
                Text(text = stringResource(R.string.new_goal))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun HomeLandscape(
    taskState: TaskState,
    routineState: RoutineState,
    goalState: GoalState,
    onNewTaskSelected: () -> Unit,
    onNewRoutineSelected: () -> Unit,
    onNewGoalSelected: () -> Unit
){
    val dateItems = generateDateItems()

    Row {
        DateCarousel(dateItems = dateItems)

        Button(
            onClick = {
                onNewTaskSelected()
            }
        ) {
            Row {
                Text(text = stringResource(R.string.new_task))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = ""
                )
            }
        }
        Button(
            onClick = {
                onNewRoutineSelected()
            }
        ) {
            Row {
                Text(text = stringResource(R.string.new_routine))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = ""
                )
            }
        }
        Button(
            onClick = {
                onNewGoalSelected()
            }
        ) {
            Row {
                Text(text = stringResource(R.string.new_goal))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = ""
                )
            }
        }
    }
}
