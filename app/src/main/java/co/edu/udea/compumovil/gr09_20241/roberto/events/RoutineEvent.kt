package co.edu.udea.compumovil.gr09_20241.roberto.events

import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.Routine

sealed interface RoutineEvent {
    object SaveRoutine: RoutineEvent
    data class SetTitle(val title: String): RoutineEvent
    data class SetDescription(val description: String): RoutineEvent
    data class SetFrequency(val frequency: String): RoutineEvent
    data class SetSessionTime(val sessionTime: Float): RoutineEvent
    data class DeleteRoutine(val routine: Routine): RoutineEvent
}