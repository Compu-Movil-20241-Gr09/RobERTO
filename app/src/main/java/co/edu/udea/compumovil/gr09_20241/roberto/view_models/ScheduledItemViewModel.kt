package co.edu.udea.compumovil.gr09_20241.roberto.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr09_20241.roberto.data.DateItem
import co.edu.udea.compumovil.gr09_20241.roberto.data.states.ScheduledItemsState
import co.edu.udea.compumovil.gr09_20241.roberto.database.routine.Routine
import co.edu.udea.compumovil.gr09_20241.roberto.database.scheduledItem.ScheduledItemDAO
import co.edu.udea.compumovil.gr09_20241.roberto.database.task.Task
import co.edu.udea.compumovil.gr09_20241.roberto.utils.scheduleItemsUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ScheduledItemViewModel(
    private val dao: ScheduledItemDAO,
) : ViewModel() {
    private val _scheduledItems = dao.getAllScheduledItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ScheduledItemsState())

    val state = combine(_state, _scheduledItems){ state, scheduledItems ->
        state.copy(
            scheduledItems = scheduledItems
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ScheduledItemsState())

    fun scheduleItems(
        dates: List<DateItem>,
        tasks: List<Task>,
        routines: List<Routine>
    ){
        scheduleItemsUtil(
            dates = dates,
            tasks = tasks,
            routines = routines,
            scheduledItemDao = dao
        )
    }
}