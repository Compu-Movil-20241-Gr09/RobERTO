package co.edu.udea.compumovil.gr09_20241.roberto.ui.login_register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.events.UserEvent
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.UserState
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.UserViewModel
import kotlinx.coroutines.delay
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.*
import co.edu.udea.compumovil.gr09_20241.roberto.ui.theme.RobertoColor

@Preview
@Composable
fun LoginScreen(
    userViewModel: UserViewModel,
    onUserEvent: (UserEvent) -> Unit,
    onUserValidNav: () -> Unit,
) {
    val userState by userViewModel.state.collectAsState()

    LaunchedEffect(userState.isLoggedIn) {
        if (userState.isLoggedIn) {
            onUserValidNav()
        }
    }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        Box {
            LoginPortrait(
                Modifier.align(Alignment.Center),
                userState,
                onUserEvent,
                onUserValidNav
            )
        }
    }
}

@Composable
fun LoginPortrait(
    modifier: Modifier,
    state: UserState,
    onUserEvent: (UserEvent) -> Unit,
    onUserValidNav: () -> Unit,
) {
    var showEmailError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }

    LaunchedEffect(state.showUserNotFoundError) {
        if (state.showUserNotFoundError) {
            showEmailError = true
            delay(1000)
            showEmailError = false
        }
    }

    LaunchedEffect(state.showPasswordError) {
        if (state.showPasswordError) {
            showPasswordError = true
            delay(1000)
            showPasswordError = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth() // Occupy full width
            .padding(24.dp)
    ) {// Add padding around the content)
        // HeaderImage(Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.padding(12.dp))

        Text( // Welcome Text
            text = "Welcome to RobERTO!",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black // Adjust color as needed
        )
        Spacer(modifier = Modifier.padding(30.dp))

        TextField(
            // Email Field
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = state.email,
            onValueChange = {
                onUserEvent(UserEvent.SetEmail(it))
            },
            isError = !state.isEmailValid // Set error state based on isEmailValid
        )

        if (showEmailError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.Transparent)

            ) {
                Text(
                    text = "ERROR: EMAIL NO EXISTE",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        @OptIn(ExperimentalMaterial3Api::class)
        TextField( // Password Field
            placeholder = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = state.password,
            onValueChange = {
                onUserEvent(UserEvent.SetPassword(it))
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = !state.isPasswordValid // Set error state based on isPasswordValid
        )

        if (showPasswordError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "ERROR: CONTRASEÑA INCORRECTA",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.padding(40.dp))

        Button(
            // Login Button
            modifier = Modifier
                .width(130.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally), // Color similar a las vistas de Figma
            colors = ButtonDefaults.buttonColors(
                disabledContentColor = Color.White,
                contentColor = Color.White,
                containerColor = Color(0xFF03A9F4),
            ),
            onClick = {
                onUserEvent(UserEvent.ValidateUser)
            },
        ) {
            Text(text = "Log in")
        }

        Spacer(modifier = Modifier.padding(4.dp))
    }
}

