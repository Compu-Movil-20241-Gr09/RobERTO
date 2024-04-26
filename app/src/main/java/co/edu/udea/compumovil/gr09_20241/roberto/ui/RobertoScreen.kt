package co.edu.udea.compumovil.gr09_20241.roberto.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.HomeScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.NewGoalScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.NewRoutineScreen
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.GoalViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel

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

@Composable
fun RobertoApp(
    taskViewModel: TaskViewModel = viewModel(),
    routineViewModel: RoutineViewModel = viewModel(),
    goalViewModel: GoalViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = RobertoScreen.valueOf(
        backStackEntry?.destination?.route ?: RobertoScreen.Home.name
    )

    Scaffold(
        topBar = {
            RobertoAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = RobertoScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
            composable(route = RobertoScreen.Home.name){
                HomeScreen(
                    taskViewModel = taskViewModel,
                    routineViewModel = routineViewModel,
                    goalViewModel = goalViewModel,
                    onNewTaskSelected = {
                        navController.navigate(RobertoScreen.NewTask.name)
                    },
                    onNewRoutineSelected = {
                        navController.navigate(RobertoScreen.NewRoutine.name)
                    },
                    onNewGoalSelected = {
                        navController.navigate(RobertoScreen.NewGoal.name)
                    }
                )
            }
            composable(route = RobertoScreen.NewTask.name){
                /*NewTaskScreen(
                    taskViewModel = taskViewModel,
                    onEvent = taskViewModel::onEvent,
                    onTaskCreatedNav = {
                        navController.navigate(RobertoScreen.Home.name)
                    }
                )*/
            }
            composable(route = RobertoScreen.NewRoutine.name){
                NewRoutineScreen(
                    routineViewModel = routineViewModel,
                    onEvent = routineViewModel::onEvent,
                    onRoutineCreatedNav = {
                        navController.navigate(RobertoScreen.Home.name)
                    }
                )
            }
            composable(route = RobertoScreen.NewGoal.name){
                NewGoalScreen(
                    goalViewModel = goalViewModel,
                    onEvent = goalViewModel::onEvent,
                    onGoalCreatedNav = {
                        navController.navigate(RobertoScreen.Home.name)
                    }
                )
            }
        }
    }
}




