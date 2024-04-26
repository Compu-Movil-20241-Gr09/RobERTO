package co.edu.udea.compumovil.gr09_20241.roberto.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.edu.udea.compumovil.gr09_20241.roberto.R

enum class RobertoScreen(@StringRes val title: Int){
    Login(title = R.string.welcome),
    Home(title = R.string.home),
    NewTask(title = R.string.new_task),
    NewRoutine(title = R.string.new_routine),
    NewGoal(title = R.string.new_goal)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RobertoAppBar(
    currentScreen: RobertoScreen,
    canNavigateBack: Boolean,
    navigateUp:() -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}




