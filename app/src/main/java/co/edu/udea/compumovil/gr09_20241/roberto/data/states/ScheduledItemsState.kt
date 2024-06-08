package co.edu.udea.compumovil.gr09_20241.roberto.data.states

import co.edu.udea.compumovil.gr09_20241.roberto.database.scheduledItem.ScheduledItem


data class ScheduledItemsState (
    val scheduledItems: List<ScheduledItem> = emptyList()
)