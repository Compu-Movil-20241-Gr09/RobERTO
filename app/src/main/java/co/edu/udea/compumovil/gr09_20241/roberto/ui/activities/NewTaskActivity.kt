package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.TaskState
import co.edu.udea.compumovil.gr09_20241.roberto.events.TaskEvent
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.NumberInput
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.TextFieldDatePicker
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel

@Composable
fun NewTaskScreen(

    taskViewModel: TaskViewModel,
    onEvent: (TaskEvent) -> Unit,
    onTaskCreatedNav: () -> Unit
) {

    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val taskState by taskViewModel.state.collectAsState()

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        NewTaskPortrait(
            taskState,
            onEvent,
            onTaskCreatedNav
        )
    }else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
        NewTaskLandscape(
            taskState,
            onEvent,
            onTaskCreatedNav
        )
    }
}

@Composable
fun NewTaskPortrait(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
    onTaskCreatedNav: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = state.title,
            onValueChange = {
                onEvent(TaskEvent.SetTitle(it))
            },
            label = {
                    Text(text = stringResource(R.string.title))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        // Description TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = state.description,
            onValueChange = {
                onEvent(TaskEvent.SetDescription(it))
            },
            label = {
                Text(text = stringResource(R.string.description))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            singleLine = false,
            maxLines = 5
        )

        // DatePicker for dueDate
        TextFieldDatePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = stringResource(R.string.deadline),
            value = state.deadline,
            onValueChange = { onEvent(TaskEvent.SetDeadline(it)) }
        )

        // NumberInput for Estimated time
        Text(
            modifier = Modifier,
            text = stringResource(R.string.estimated_time),
            style = MaterialTheme.typography.headlineSmall
        )
        NumberInput(
            modifier = Modifier
                .fillMaxWidth(),
            onValueChanged = {onEvent(TaskEvent.SetEstimatedTime(it))},
            label = stringResource(R.string.estimated_time)
        )

        // Create Button
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Button(
                onClick = {
                    onEvent(TaskEvent.SaveTask)
                    onTaskCreatedNav()
                },
                enabled = isNewTaskValid(state)
            ) {
                Row {
                    Text(text = stringResource(R.string.create))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = ""
                    )
                }
            }
        }
    }

}

@Composable
fun NewTaskLandscape(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
    onTaskCreatedNav: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = state.title,
            onValueChange = {
                onEvent(TaskEvent.SetTitle(it))
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            singleLine = true
        )

        // Description TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = state.description,
            onValueChange = {
                onEvent(TaskEvent.SetDescription(it))
            },
            label = {
                Text(text = stringResource(R.string.description))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            singleLine = false,
            maxLines = 5
        )

        // DatePicker for dueDate
        Text(
            modifier = Modifier,
            text = stringResource(R.string.repeat),
            style = MaterialTheme.typography.headlineSmall
        )
        TextFieldDatePicker(
            label = "Deadline",
            value = state.deadline,
            onValueChange = { onEvent(TaskEvent.SetDeadline(it)) }
        )

        // NumberInput for Estimated time
        Text(
            modifier = Modifier,
            text = stringResource(R.string.estimated_time),
            style = MaterialTheme.typography.headlineSmall
        )
        NumberInput(
            modifier = Modifier
                .fillMaxWidth(),
            onValueChanged = {onEvent(TaskEvent.SetEstimatedTime(it))},
            label = stringResource(R.string.estimated_time)
        )

        // Create Button
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Button(
                onClick = {
                    onEvent(TaskEvent.SaveTask)
                    onTaskCreatedNav()
                },
                enabled = isNewTaskValid(state)
            ) {
                Row {
                    Text(text = stringResource(R.string.create))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

fun isNewTaskValid( state: TaskState) : Boolean {
    return state.title.isNotBlank() && state.deadline.isNotBlank() && state.estimatedTime != 0f
}
