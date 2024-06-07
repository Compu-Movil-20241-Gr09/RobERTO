package co.edu.udea.compumovil.gr09_20241.roberto.data.states

import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.Goal

data class GoalState(
    val goals: List<Goal> = emptyList(),
    val title: String = "",
    val description: String = "",
    val steps: String = ""
)
