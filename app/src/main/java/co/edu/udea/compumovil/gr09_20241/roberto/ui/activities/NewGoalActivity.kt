package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.GoalState
import co.edu.udea.compumovil.gr09_20241.roberto.events.GoalEvent
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.GoalViewModel

@Composable
fun NewGoalScreen(
    goalViewModel: GoalViewModel,
    onEvent: (GoalEvent) -> Unit,
    onGoalCreatedNav: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    val goalState by goalViewModel.state.collectAsState()

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        NewGoalPortrait(
            goalState,
            onEvent,
            onGoalCreatedNav
        )
    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        NewGoalLandscape(
            goalState,
            onEvent,
            onGoalCreatedNav
        )
    }
}

@Composable
fun NewGoalPortrait(
    state: GoalState,
    onEvent: (GoalEvent) -> Unit,
    onGoalCreatedNav: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.new_goal)
        )

        // Title TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = state.title,
            onValueChange = { onEvent(GoalEvent.SetTitle(it))
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
                .padding(8.dp),
            value = state.description,
            onValueChange = {
                onEvent(GoalEvent.SetDescription(it))
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

        // Steps TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = state.steps,
            onValueChange = {
                onEvent(GoalEvent.SetSteps(it))
            },
            label = {
                Text(text = stringResource(R.string.steps))
            }
        )

        // Create Button
        Button(
            onClick = {
                onEvent(GoalEvent.SaveGoal)
                onGoalCreatedNav()
            },
            enabled = isNewGoalValid(state)
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

@Composable
fun NewGoalLandscape(
    state: GoalState,
    onEvent: (GoalEvent) -> Unit,
    onGoalCreatedNav: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.new_goal)
        )

        // Title TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = state.title,
            onValueChange = { onEvent(GoalEvent.SetTitle(it))
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
                .padding(8.dp),
            value = state.description,
            onValueChange = {
                onEvent(GoalEvent.SetDescription(it))
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

        // Steps TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = state.steps,
            onValueChange = {
                onEvent(GoalEvent.SetSteps(it))
            },
            label = {
                Text(text = stringResource(R.string.steps))
            }
        )

        // Create Button
        Button(
            onClick = {
                onEvent(GoalEvent.SaveGoal)
                onGoalCreatedNav()
            },
            enabled = isNewGoalValid(state)
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

fun isNewGoalValid( state: GoalState) : Boolean {
    return state.title.isNotBlank() && state.description.isNotBlank() && state.steps.isNotBlank()
}

@Preview
// Preview function to display the NewGoalActivity layout
// You can provide sample data here to see how the layout looks
@Composable
fun NewGoalPortraitPreview(
    state: GoalState,
    onEvent: (GoalEvent) -> Unit,
    onGoalCreatedNav: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(R.string.new_goal)
        )

        // Title TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = state.title,
            onValueChange = { onEvent(GoalEvent.SetTitle(it))
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
                .padding(8.dp),
            value = state.description,
            onValueChange = {
                onEvent(GoalEvent.SetDescription(it))
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

        // Steps TextField
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = state.steps,
            onValueChange = {
                onEvent(GoalEvent.SetSteps(it))
            },
            label = {
                Text(text = stringResource(R.string.steps))
            }
        )

        // Create Button
        Button(
            onClick = {
                onEvent(GoalEvent.SaveGoal)
                onGoalCreatedNav()
            },
            enabled = isNewGoalValid(state)
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



