package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableList(
    modifier: Modifier = Modifier,
    text: String,
    items: List<String>
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(4.dp)
        ){
            Text(text = text)
        }

        if(expanded){
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { item ->
                    Text(text = item)
                    Log.i("Elpepe", item)
                }
            }
        }
    }
}