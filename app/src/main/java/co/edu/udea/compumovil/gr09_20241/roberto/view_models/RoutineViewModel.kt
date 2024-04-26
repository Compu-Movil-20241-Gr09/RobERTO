package co.edu.udea.compumovil.gr09_20241.roberto.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.RoutineState
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.Routine
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.RoutineDAO
import co.edu.udea.compumovil.gr09_20241.roberto.events.RoutineEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutineViewModel(
    private val dao: RoutineDAO
): ViewModel() {
    private val _routines = dao.getAllRoutines()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(RoutineState())
    val state = combine(_state, _routines){ state, routines ->
        state.copy(
            routines = routines
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RoutineState())

    fun onEvent(event: RoutineEvent){
        when(event){
            is RoutineEvent.DeleteRoutine -> {
                viewModelScope.launch {
                    dao.deleteRoutine(event.routine)
                }
            }
            RoutineEvent.SaveRoutine -> {
                val title = _state.value.title
                val description = _state.value.description
                val frecuency = _state.value.frecuency
                val sessionTime = _state.value.sessionTime

                val routine = Routine(
                    title = title,
                    description = description,
                    frecuency = frecuency,
                    sessionTime = sessionTime
                )

                viewModelScope.launch {
                    dao.upsertRoutine(routine)
                }

                _state.update { it.copy(
                    title = "",
                    description = "",
                    frecuency = "",
                    sessionTime = 0f
                ) }
            }
            is RoutineEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }
            is RoutineEvent.SetFrecuency -> {
                _state.update { it.copy(
                    frecuency = event.frecuency
                ) }
            }
            is RoutineEvent.SetSessionTime -> {
                _state.update { it.copy(
                    sessionTime = event.sessionTime
                ) }
            }
            is RoutineEvent.SetTitle -> {
                _state.update { it.copy(
                    description = event.title
                ) }
            }
        }
    }
}