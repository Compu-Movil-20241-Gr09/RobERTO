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
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.RoutineState
import co.edu.udea.compumovil.gr09_20241.roberto.events.RoutineEvent
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.DaysSelector
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.NumberInput
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel

@Composable
fun NewRoutineScreen(
    routineViewModel: RoutineViewModel,
    onEvent: (RoutineEvent) -> Unit,
    onRoutineCreatedNav: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val routineState by routineViewModel.state.collectAsState()

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        NewRoutinePortrait(
            routineState,
            onEvent,
            onRoutineCreatedNav
        )
    }else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
        NewRoutineLandscape(
            routineState,
            onEvent,
            onRoutineCreatedNav
        )
    }
}

@Composable
fun NewRoutinePortrait(
    state: RoutineState,
    onEvent: (RoutineEvent) -> Unit,
    onRoutineCreatedNav: () -> Unit
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
                onEvent(RoutineEvent.SetTitle(it))
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
                onEvent(RoutineEvent.SetDescription(it))
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

        // DaySelector for frecuency
        Text(
            modifier = Modifier,
            text = stringResource(R.string.repeat),
            style = MaterialTheme.typography.headlineSmall
        )
        DaysSelector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            selectedDays = state.frecuency,
            onDaySelectedChange = { onEvent(RoutineEvent.SetFrecuency(it)) }
        )

        // NumberInput for session time
        Text(
            modifier = Modifier,
            text = stringResource(R.string.session_time),
            style = MaterialTheme.typography.headlineSmall
        )
        NumberInput(
            modifier = Modifier
                .fillMaxWidth(),
            onValueChanged = {onEvent(RoutineEvent.SetSessionTime(it))},
            label = stringResource(R.string.session_time)
        )

        // Create Button
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Button(
                onClick = {
                    onEvent(RoutineEvent.SaveRoutine)
                    onRoutineCreatedNav()
                },
                enabled = isNewRoutineValid(state)
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
fun NewRoutineLandscape(
    state: RoutineState,
    onEvent: (RoutineEvent) -> Unit,
    onRoutineCreatedNav: () -> Unit
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
                onEvent(RoutineEvent.SetTitle(it))
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
                onEvent(RoutineEvent.SetDescription(it))
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

        // DaySelector for frecuency
        Text(
            modifier = Modifier,
            text = stringResource(R.string.repeat),
            style = MaterialTheme.typography.headlineSmall
        )
        DaysSelector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            selectedDays = state.frecuency,
            onDaySelectedChange = { onEvent(RoutineEvent.SetFrecuency(it)) }
        )

        // NumberInput for session time
        Text(
            modifier = Modifier,
            text = stringResource(R.string.session_time),
            style = MaterialTheme.typography.headlineSmall
        )
        NumberInput(
            modifier = Modifier
                .fillMaxWidth(),
            onValueChanged = {onEvent(RoutineEvent.SetSessionTime(it))},
            label = stringResource(R.string.session_time)
        )

        // Create Button
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Button(
                onClick = {
                    onEvent(RoutineEvent.SaveRoutine)
                    onRoutineCreatedNav()
                },
                enabled = isNewRoutineValid(state)
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

fun isNewRoutineValid( state: RoutineState) : Boolean {
    return state.title.isNotBlank() && state.frecuency.isNotBlank() && state.sessionTime != 0f
}



