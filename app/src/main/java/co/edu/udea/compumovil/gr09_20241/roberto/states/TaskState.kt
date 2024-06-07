package co.edu.udea.compumovil.gr09_20241.roberto.states

import co.edu.udea.compumovil.gr09_20241.roberto.database.task.Task

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val title: String = "",
    val description: String = "",
    val deadline:String = "",
    val estimatedTime: Float = 0f
)