package co.edu.udea.compumovil.gr09_20241.roberto.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.HomeScreen

import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.NewGoalScreen

import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.ListItemsScreen

import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.NewRoutineScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.activities.NewTaskScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.RobertoBottomAppBar
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.RobertoTopAppBar
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.GoalViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel

enum class RobertoScreen(@StringRes val title: Int){
    Login(title = R.string.welcome),
    Home(title = R.string.home),
    NewTask(title = R.string.new_task),
    NewRoutine(title = R.string.new_routine),
    NewGoal(title = R.string.new_goal),
    ListItems(title = R.string.elements_lists)
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
            RobertoTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            RobertoBottomAppBar(navController = navController)
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
                    routineViewModel = routineViewModel
                )
            }
            composable(route = RobertoScreen.NewTask.name){
                NewTaskScreen(
                    taskViewModel = taskViewModel,
                    onEvent = taskViewModel::onEvent,
                    onTaskCreatedNav = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = RobertoScreen.NewRoutine.name){
                NewRoutineScreen(
                    routineViewModel = routineViewModel,
                    onEvent = routineViewModel::onEvent,
                    onRoutineCreatedNav = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = RobertoScreen.NewGoal.name){
                NewGoalScreen(
                    goalViewModel = goalViewModel,
                    onEvent = goalViewModel::onEvent,
                    onGoalCreatedNav = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = RobertoScreen.ListItems.name){
                ListItemsScreen(
                    taskViewModel = taskViewModel,
                    routineViewModel = routineViewModel,
                    goalViewModel = goalViewModel
                )
            }
        }
    }
}




