package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScheduledCard(
    title: String,
    type: String,
    time: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        onClick = { /*TODO*/ }
    ) {
        Text(
            text = title,
            modifier = Modifier.
            padding(16.dp, 16.dp, 16.dp, 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                text = type,
                modifier = Modifier.
                padding(16.dp, 4.dp, 16.dp, 16.dp),
                fontSize = 14.sp,
            )
            Text(
                text = time,
                modifier = Modifier.
                padding(16.dp, 4.dp, 16.dp, 16.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.End,
            )
        }
    }
}