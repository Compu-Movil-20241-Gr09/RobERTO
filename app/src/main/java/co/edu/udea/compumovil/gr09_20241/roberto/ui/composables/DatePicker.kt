package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Date

@Composable
fun TextFieldDatePicker(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: ((String) -> Unit)
) {
    val mContext = LocalContext.current

    // Fetching current year, month, and day
    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month, and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            // Format date as dd/MM/yyyy
            val formattedDay = if (mDayOfMonth < 10) "0$mDayOfMonth" else "$mDayOfMonth"
            val formattedMonth = if (mMonth + 1 < 10) "0${mMonth + 1}" else "${mMonth + 1}"
            val formattedDate = "$formattedDay/$formattedMonth/$mYear"
            onValueChange(formattedDate)
        }, mYear, mMonth, mDay
    )

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = modifier
                .clickable { mDatePickerDialog.show() },
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date")
            },
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = MaterialTheme.colorScheme.onSurface,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                // For Icons
                disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}