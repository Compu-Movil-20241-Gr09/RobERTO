package co.edu.udea.compumovil.gr09_20241.roberto.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.GoalState
import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.Goal
import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.GoalDAO
import co.edu.udea.compumovil.gr09_20241.roberto.events.GoalEvent
import co.edu.udea.compumovil.gr09_20241.roberto.events.RoutineEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoalViewModel(
    private val dao: GoalDAO
): ViewModel() {

    private val _goals = dao.getAllGoals()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(GoalState())
    val state = combine(_state, _goals){ state, goals ->
        state.copy(
            goals = goals
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GoalState())

    fun onEvent(event: GoalEvent){
        when(event){
            is GoalEvent.DeleteGoal -> {
                viewModelScope.launch {
                    dao.deleteGoal(event.goal)
                }
            }
            GoalEvent.SaveGoal -> {
                val title = _state.value.title
                val description = _state.value.description
                val steps = _state.value.steps

                val goal = Goal(
                    title = title,
                    description = description,
                    steps = steps
                )

                viewModelScope.launch {
                    dao.upsertGoal(goal)
                }
                _state.update { it.copy(
                    title = "",
                    description = "",
                    steps = ""
                ) }
            }
            is GoalEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }
            is GoalEvent.SetSteps -> {
                _state.update { it.copy(
                    steps = event.steps
                ) }
            }
            is GoalEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }
        }
    }
}