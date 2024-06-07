package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.ui.RobertoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RobertoBottomAppBar(
    navController: NavHostController
) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { navController.navigate(RobertoScreen.ListItems.name) }) {
                Icon(Icons.AutoMirrored.Filled.List, contentDescription = "List")
            }
            IconButton(onClick = {
                isSheetOpen = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
            if(isSheetOpen){
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = { isSheetOpen = false }
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 4.dp),
                        onClick = {
                            navController.navigate(RobertoScreen.NewTask.name)
                            isSheetOpen = false
                        }
                    ) {
                        Text(text = stringResource(R.string.new_task))
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 4.dp),
                        onClick = {
                            navController.navigate(RobertoScreen.NewRoutine.name)
                            isSheetOpen = false
                        }
                    ) {
                        Text(text = stringResource(R.string.new_routine))
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 4.dp),
                        onClick = {
                            navController.navigate(RobertoScreen.NewGoal.name)
                            isSheetOpen = false
                        }
                    ) {
                        Text(text = stringResource(R.string.new_goal))
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}