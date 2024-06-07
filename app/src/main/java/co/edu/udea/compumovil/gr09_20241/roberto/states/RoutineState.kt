package co.edu.udea.compumovil.gr09_20241.roberto.states

import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.Routine

data class RoutineState(
    val routines: List<Routine> = emptyList(),
    val title: String = "",
    val description: String = "",
    val frecuency: String = "",
    val sessionTime: Float = 0f
)
