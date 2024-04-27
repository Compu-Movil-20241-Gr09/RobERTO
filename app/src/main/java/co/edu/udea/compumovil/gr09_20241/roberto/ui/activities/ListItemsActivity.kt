package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.GoalState
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.RoutineState
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.TaskState
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.ExpandableList
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
        ExpandableList(
            text = "Tasks",
            items = taskState.tasks.map { it.title }
        )
        ExpandableList(
            text = "Routines",
            items = routineState.routines.map { it.title }
        )
        ExpandableList(
            text = "Goal",
            items = goalState.goals.map { it.title }
        )
    }
}