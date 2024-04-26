package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun NumberInput(
    initialValue: Float = 0f,
    onValueChanged: (Float) -> Unit,
    label: String
) {
    var currentValue by remember {
        mutableFloatStateOf(initialValue)
    }
    var inputText by remember {
        mutableStateOf("")
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(onClick = {
            currentValue = max(0f, currentValue.dec())
            inputText = currentValue.toString()
        }) {
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Decrement")
        }
        OutlinedTextField(
            value = inputText,
            onValueChange = { newInputText ->
                inputText = newInputText
                if (newInputText.isNotEmpty()) {
                    newInputText.toFloatOrNull()?.let {
                        currentValue = max(0.0f, it)
                        onValueChanged(it)
                    }
                } else {
                    currentValue = 0.0f
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .weight(1f),
            label = { Text(text = label) },
            placeholder = { Text("0.0") },
            suffix = { Text(text = "Hours") },
            singleLine = true
        )
        IconButton(onClick = {
            currentValue = currentValue.inc()
            inputText = currentValue.toString()
        }) {
            Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "Increment")
        }
    }
}