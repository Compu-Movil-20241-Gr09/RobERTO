package co.edu.udea.compumovil.gr09_20241.roberto.events

import co.edu.udea.compumovil.gr09_20241.roberto.database.task.Task

sealed interface TaskEvent {
    object SaveTask: TaskEvent
    data class SetTitle(val title: String): TaskEvent
    data class SetDescription(val description: String): TaskEvent
    data class SetDeadline(val deadline: String): TaskEvent
    data class SetEstimatedTime(val estimatedTime: Float): TaskEvent
    data class DeleteTask(val task: Task): TaskEvent
}

