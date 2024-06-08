package co.edu.udea.compumovil.gr09_20241.roberto.events

import co.edu.udea.compumovil.gr09_20241.roberto.database.user.User

sealed interface UserEvent {
    object SaveUser : UserEvent
    object ValidateUser : UserEvent
    data class SetName(val name: String) : UserEvent
    data class SetEmail(val email: String) : UserEvent
    data class SetPassword(val password: String) : UserEvent

    data class SetConfirmPassword(val confirmPassword: String) : UserEvent

    data class DeleteContact(val user: User) : UserEvent

}