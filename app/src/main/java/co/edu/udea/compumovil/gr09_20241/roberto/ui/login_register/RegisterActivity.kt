package co.edu.udea.compumovil.gr09_20241.roberto.ui.login_register

import android.util.Patterns
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.events.UserEvent
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.UserState
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.UserViewModel
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.*

@Composable
fun RegisterScreen(
    userViewModel: UserViewModel,
    onUserEvent: (UserEvent) -> Unit,
    onUserCreatedNav: () -> Unit,
) {
    val userState by userViewModel.state.collectAsState()

    Box(
        Modifier
            .fillMaxSize()

    ) {

        Box {
            RegisterPortrait(
                Modifier.align(Alignment.Center),
                userState,
                onUserEvent,
                onUserCreatedNav
            )
        }
    }
}

@Composable
fun RegisterPortrait(
    modifier: Modifier,
    userState: UserState,
    onUserEvent: (UserEvent) -> Unit,
    onUserCreatedNav: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth() // Occupy full width
            .padding(32.dp)
    ) {// Add padding around the content)
        // HeaderImage(Modifier.align(Alignment.CenterHorizontally))


        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            // Name TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Full name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = userState.name,
            onValueChange = {
                onUserEvent(UserEvent.SetName(it))
            },
        )

        Spacer(modifier = Modifier.padding(12.dp))

        TextField(
            // Email TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = userState.email,
            onValueChange = {
                onUserEvent(UserEvent.SetEmail(it))
            },
        )

        Spacer(modifier = Modifier.padding(12.dp))

        @OptIn(ExperimentalMaterial3Api::class)
        TextField( // Password TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = userState.password,
            onValueChange = {
                onUserEvent(UserEvent.SetPassword(it))
            }
        )

        Spacer(modifier = Modifier.padding(12.dp))

        TextField(
            // Password Confirmation TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Confirmar contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = userState.confirmPassword,
            onValueChange = {
                onUserEvent(UserEvent.SetConfirmPassword(it))
            },
        )

        Spacer(modifier = Modifier.padding(50.dp))

        Button(
            modifier = Modifier
                .width(120.dp)
                .height(50.dp) // Color similar a las vistas de Figma
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                disabledContentColor = Color.White,
                contentColor = Color.White,
                containerColor = Color(0xFF03A9F4),
            ),
            onClick = {
                onUserEvent(UserEvent.SaveUser)
                onUserCreatedNav()
            },
            enabled = isRegisterValid(userState),
        ) {
            Text(text = "Register")
        }
    }
}

fun isRegisterValid(state: UserState): Boolean {
    return state.name.isNotBlank()
            && state.email.isNotBlank()
            && Patterns.EMAIL_ADDRESS.matcher(state.email).matches()
            && state.password.isNotBlank()
            && state.confirmPassword.isNotBlank()
            && state.password == state.confirmPassword
            && state.password.length >= 6
}