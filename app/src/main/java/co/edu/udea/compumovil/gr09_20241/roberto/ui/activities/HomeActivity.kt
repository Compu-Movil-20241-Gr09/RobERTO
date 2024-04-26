package co.edu.udea.compumovil.gr09_20241.roberto.ui.activities

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr09_20241.roberto.data.generateDateItems
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.TaskState
import co.edu.udea.compumovil.gr09_20241.roberto.events.TaskEvent
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.DateCarousel

@Composable
fun HomeScreen(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit
){
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        HomePortrait()
    }else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
        HomeLandscape()
    }
}

@Composable
fun HomePortrait(){
    //val dateItems = generateDateItems()

    Column {
        DateCarousel(dateItems = emptyList())
    }
}

@Composable
fun HomeLandscape(){

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePortraitPreview(){
    MaterialTheme{
        HomePortrait()
    }
}