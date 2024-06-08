package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.GoalState
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.RoutineState
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.TaskState
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.ExpandableList
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.ItemCard
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.GoalViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel

@Composable
fun ListItemsScreen(
    taskViewModel: TaskViewModel,
    routineViewModel: RoutineViewModel,
    goalViewModel: GoalViewModel
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val taskState by taskViewModel.state.collectAsState()
    val routineState by routineViewModel.state.collectAsState()
    val goalState by goalViewModel.state.collectAsState()

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        ListItemsPortrait(
            taskState = taskState,
            routineState = routineState,
            goalState = goalState
        )
    }else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
        Text(text = "hola")
    }
}

@Composable
fun ListItemsPortrait(
    taskState: TaskState,
    routineState: RoutineState,
    goalState: GoalState
) {
    Column {
        var expanded by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(4.dp)
            ){
                Text(text = stringResource(R.string.tasks))
            }

            if(expanded){
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    taskState.tasks.forEach { item ->
                        item.description?.let {
                            item.deadline?.let { it1 ->
                                ItemCard(
                                    title = item.title,
                                    description = it,
                                    extraInfo = it1,
                                    backgroundColor= MaterialTheme.colorScheme.secondary,
                                    onEditClick= { /*TODO*/ },
                                    onDeleteClick= { /*TODO*/ }
                                )
                            }
                        }

                    }
                }
            }
        }

        var expandedRoutine by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedRoutine = !expandedRoutine }
                    .padding(4.dp)
            ){
                Text(text = stringResource(R.string.routines))
            }

            if(expandedRoutine){
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    routineState.routines.forEach { item ->
                        item.description?.let {
                            ItemCard(
                                title = item.title,
                                description = it,
                                extraInfo = item.frequency,
                                backgroundColor= MaterialTheme.colorScheme.tertiary,
                                onEditClick= { /*TODO*/ },
                                onDeleteClick= { /*TODO*/ }
                            )
                        }
                    }
                }
            }
        }

        var expandedGoal by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expandedGoal = !expandedGoal }
                    .padding(4.dp)
            ){
                Text(text = stringResource(R.string.goals))
            }

            if(expandedGoal){
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    goalState.goals.forEach { item ->
                        item.steps?.let {
                            item.description?.let { it1 ->
                                ItemCard(
                                    title = item.title,
                                    description = it1,
                                    extraInfo = it,
                                    backgroundColor= MaterialTheme.colorScheme.secondary,
                                    onEditClick= { /*TODO*/ },
                                    onDeleteClick= { /*TODO*/ }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}