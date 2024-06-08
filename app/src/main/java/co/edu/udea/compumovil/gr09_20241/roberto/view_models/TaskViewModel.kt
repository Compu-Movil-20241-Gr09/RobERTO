package co.edu.udea.compumovil.gr09_20241.roberto.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.TaskState
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.Task
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.TaskDAO
import co.edu.udea.compumovil.gr09_20241.roberto.events.TaskEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel(
    private val dao: TaskDAO
): ViewModel() {

    private val _tasks = dao.getTasksOrderedByDeadline()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _state = MutableStateFlow(TaskState())
    val state = combine(_state, _tasks) { state, tasks ->
        state.copy(
             tasks = tasks
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskState())


    fun onEvent(event: TaskEvent){
        when(event){
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    dao.deleteTask(event.task)
                }
            }
            is TaskEvent.SaveTask -> {
                val title = _state.value.title
                val description = _state.value.description
                val deadline = _state.value.deadline
                val estimatedTime = _state.value.estimatedTime

                val task = Task(
                    title = title,
                    description = description,
                    deadline = deadline,
                    estimatedTime = estimatedTime
                )

                viewModelScope.launch {
                    dao.upsertTask(task)
                }
                _state.update { it.copy(
                    title = "",
                    description = "",
                    deadline = "",
                    estimatedTime = 0f
                ) }
            }
            is TaskEvent.SetDeadline -> {
                _state.update { it.copy(
                    deadline = event.deadline
                ) }
            }
            is TaskEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }
            is TaskEvent.SetEstimatedTime -> {
                _state.update { it.copy(
                    estimatedTime = event.estimatedTime
                ) }
            }
            is TaskEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }
        }
    }
}