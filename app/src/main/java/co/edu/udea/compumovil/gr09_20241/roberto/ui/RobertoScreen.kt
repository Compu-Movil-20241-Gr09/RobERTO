package co.edu.udea.compumovil.gr09_20241.roberto.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import co.edu.udea.compumovil.gr09_20241.roberto.ui.login_register.LoginRegisterScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.login_register.LoginScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.login_register.RegisterScreen
import co.edu.udea.compumovil.gr09_20241.roberto.ui.theme.RobertoColor
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.GoalViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.RoutineViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.ScheduledItemViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.TaskViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.UserViewModel

enum class RobertoScreen(@StringRes val title: Int) {
    LoginRegister(title = R.string.app_name),
    Login(title = R.string.login),
    Register(title = R.string.register),
    Home(title = R.string.home),
    NewTask(title = R.string.new_task),
    NewRoutine(title = R.string.new_routine),
    NewGoal(title = R.string.new_goal),
    ListItems(title = R.string.elements_lists)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RobertoTopAppBar(
    currentScreen: RobertoScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(currentScreen.title),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = RobertoColor
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RobertoBottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
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


            IconButton(onClick = { isSheetOpen = true }) {
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

@Composable
fun RobertoApp(
    taskViewModel: TaskViewModel = viewModel(),
    routineViewModel: RoutineViewModel = viewModel(),
    goalViewModel: GoalViewModel = viewModel(),
    scheduledItemViewModel : ScheduledItemViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
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
                navigateUp = { navController.navigateUp() },
                modifier = Modifier.background(RobertoColor)
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = RobertoScreen.LoginRegister.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
            composable(route = RobertoScreen.Home.name) {
                HomeScreen(
                    taskViewModel = taskViewModel,
                    routineViewModel = routineViewModel,
                    scheduledItemViewModel = scheduledItemViewModel,
                    navController = navController
                )
            }
            composable(route = RobertoScreen.LoginRegister.name) {
                LoginRegisterScreen(
                    userViewModel = userViewModel,
                    onLoginSelected = {
                        navController.navigate(route = RobertoScreen.Login.name)
                    },
                    onRegisterSelected = {
                        navController.navigate(route = RobertoScreen.Register.name)
                    },
                )
            }
            composable(route = RobertoScreen.Login.name) {
                LoginScreen(
                    userViewModel = userViewModel,
                    onUserEvent = userViewModel::onUserEvent,
                    onUserValidNav = {
                        navController.navigate(route = RobertoScreen.Home.name)
                    }
                )
            }
            composable(route = RobertoScreen.Register.name) {
                RegisterScreen(
                    userViewModel = userViewModel,
                    onUserEvent = userViewModel::onUserEvent,
                    onUserCreatedNav = {
                        navController.navigate(route = RobertoScreen.LoginRegister.name)
                    }
                )
            }
            composable(route = RobertoScreen.Home.name) {
                HomeScreen(
                    taskViewModel = taskViewModel,
                    routineViewModel = routineViewModel,
                    scheduledItemViewModel = scheduledItemViewModel,
                    navController = navController
                )
            }
            composable(route = RobertoScreen.NewTask.name) {
                NewTaskScreen(
                    taskViewModel = taskViewModel,
                    onEvent = taskViewModel::onEvent,
                    onTaskCreatedNav = {
                        navController.navigate(RobertoScreen.Home.name)
                    }
                )
            }
            composable(route = RobertoScreen.NewRoutine.name) {
                NewRoutineScreen(
                    routineViewModel = routineViewModel,
                    onEvent = routineViewModel::onEvent,
                    onRoutineCreatedNav = {
                        navController.navigate(RobertoScreen.Home.name)
                    }
                )
            }
            composable(route = RobertoScreen.NewGoal.name) {
                NewGoalScreen(
                    goalViewModel = goalViewModel,
                    onEvent = goalViewModel::onEvent,
                    onGoalCreatedNav = {
                        navController.navigate(RobertoScreen.Home.name)
                    }
                )
            }
            composable(route = RobertoScreen.ListItems.name) {
                ListItemsScreen(
                    taskViewModel = taskViewModel,
                    routineViewModel = routineViewModel,
                    goalViewModel = goalViewModel
                )
            }
        }
    }
}




