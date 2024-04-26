package co.edu.udea.compumovil.gr09_20241.roberto.events

import co.edu.udea.compumovil.gr09_20241.roberto.database.goal.Goal

sealed interface GoalEvent {
    object SaveGoal: GoalEvent
    data class SetTitle(val title: String): GoalEvent
    data class SetDescription(val description: String): GoalEvent
    data class SetSteps(val steps: String): GoalEvent
    data class DeleteGoal(val goal: Goal): GoalEvent
}