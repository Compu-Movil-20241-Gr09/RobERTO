package co.edu.udea.compumovil.gr09_20241.roberto.data.states

import co.edu.udea.compumovil.gr09_20241.roberto.database.user.User

data class UserState (
    val users: List<User> = emptyList(),
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isAddingUser: Boolean = false,
    val isLoggedIn: Boolean = false, // Add isLoggedIn property
    val isEmailValid: Boolean = true, // Add isEmailValid property
    val isPasswordValid: Boolean = true, // Add isPasswordValid property
    val showUserNotFoundError: Boolean = false, // Nueva propiedad
    val showPasswordError: Boolean = false // Nueva propiedad
)