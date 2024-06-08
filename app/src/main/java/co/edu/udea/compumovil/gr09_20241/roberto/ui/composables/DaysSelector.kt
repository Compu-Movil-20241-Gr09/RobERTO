package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.roberto.R

@Composable
fun DaysSelector(
    modifier: Modifier = Modifier,
    selectedDays: String,
    onDaySelectedChange: (String) -> Unit
) {
    val initialSelectedDays = selectedDays.split(",")
        .filter { it.isNotBlank() }
        .map { it.trim() } // Convert to list and clean up whitespace
    var selectedDaysList by remember { mutableStateOf(initialSelectedDays) }

    val dayPairs = listOf(
        Pair("MONDAY", R.string.monday),
        Pair("TUESDAY", R.string.tuesday),
        Pair("WEDNESDAY", R.string.wednesday),
        Pair("THURSDAY", R.string.thursday),
        Pair("FRIDAY", R.string.friday),
        Pair("SATURDAY", R.string.saturday),
        Pair("SUNDAY", R.string.sunday)
    )
    val activeColor = Color.Blue
    val inactiveColor = MaterialTheme.colorScheme.secondaryContainer

    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
    ) {
        dayPairs.forEach { (englishDay, stringResource) ->
            val isSelected = selectedDaysList.contains(englishDay)
            val buttonColor = if (isSelected) activeColor else inactiveColor

            OutlinedButton(
                modifier = Modifier
                    .padding(2.dp),
                onClick = {
                    selectedDaysList = if (selectedDaysList.contains(englishDay)) {
                        selectedDaysList.filter { it != englishDay }
                    } else {
                        selectedDaysList + englishDay
                    }
                    val selectedDaysString = selectedDaysList.joinToString(", ")
                    Log.i("Hola", selectedDaysString)
                    onDaySelectedChange(selectedDaysString)
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = buttonColor,
                    contentColor = Color.White
                ),
                shape = CircleShape // Use CircleShape for a circular button
            ) {
                Row {
                    Text(text = stringResource(stringResource).first().toString()) // Display localized text
                }
            }
        }
    }
}