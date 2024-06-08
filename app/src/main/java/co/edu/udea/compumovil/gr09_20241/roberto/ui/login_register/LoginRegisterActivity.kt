package co.edu.udea.compumovil.gr09_20241.roberto.ui.login_register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.roberto.R
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.UserState
import co.edu.udea.compumovil.gr09_20241.roberto.ui.composables.BackgroundImage
import co.edu.udea.compumovil.gr09_20241.roberto.ui.theme.RobertoColor
import co.edu.udea.compumovil.gr09_20241.roberto.view_models.UserViewModel


@Preview(showBackground = true)
@Composable
fun LoginRegisterScreen(
    userViewModel: UserViewModel,
    onLoginSelected: () -> Unit,
    onRegisterSelected: () -> Unit,
) {
    val userState by userViewModel.state.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .background(RobertoColor)
    ) {

        Box {
            LoginRegisterPortrait(
                modifier = Modifier,
                userState,
                onLoginSelected,
                onRegisterSelected
            )
        }
    }
}

@Composable
fun LoginRegisterPortrait(
    modifier: Modifier,
    userState: UserState,
    onLoginSelected: () -> Unit,
    onRegisterSelected: () -> Unit
) {
    Image(
        painter = painterResource(id = R.drawable.roberto_image),
        contentDescription = null,
        modifier = Modifier
            .width(400.dp)
            .height(400.dp)
    )
    Column(
        modifier = modifier
            .fillMaxWidth() // Occupy full width
            .padding(32.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {// Add padding around the content)

        Spacer(modifier = Modifier.padding(200.dp)) // Espaciador entre la imagen y los botones

        Button( // Botón de inicio de sesión
            modifier = Modifier
                .width(200.dp)
                .height(50.dp), // Color similar a las vistas de Figma
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF03A9F4),
            ),
            onClick = {
                onLoginSelected()
            }
        ) {
            Text(text = "Log in")
        }

        Spacer(modifier = Modifier.padding(6.dp))

        Button( // Botón de registro
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFF1A1A1A),
            ),
            border = BorderStroke(width = 2.dp, color = Color.Black),
            onClick = {
                onRegisterSelected()
            }
        ) {
            Text(text = "Register")
        }
    }
}
