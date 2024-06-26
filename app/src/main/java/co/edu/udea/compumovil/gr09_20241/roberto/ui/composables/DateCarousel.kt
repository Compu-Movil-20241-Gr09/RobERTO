package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.roberto.data.DateItem
import co.edu.udea.compumovil.gr09_20241.roberto.ui.theme.HighlightColor
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DateCarousel(dateItems: List<DateItem>, onDateSelected: (DateItem) -> Unit) {
    val pagerState = rememberPagerState(
        initialPage = dateItems.indexOfFirst { it.date == LocalDate.now() },
        initialPageOffsetFraction = 0f
    ) {
        dateItems.size
    }

    // Keep track of the currently selected date index
    val selectedDateIndex = remember { mutableStateOf(pagerState.currentPage) }

    LaunchedEffect(pagerState.currentPage) {
        // Update the isSelected property for the date items
        dateItems.forEachIndexed { index, dateItem ->
            dateItem.isSelected = index == pagerState.currentPage
        }
        // Call the callback function to notify the parent composable of the selected date change
        onDateSelected(dateItems[pagerState.currentPage])
    }

    HorizontalPager(
        state = pagerState,
        pageSpacing = 16.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        key = { it }
    ) { page ->
        val dateItem = dateItems[page]
        val itemSize = 100.dp
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(itemSize),
            colors = CardDefaults.cardColors(
                containerColor = if (dateItem.isSelected) HighlightColor else MaterialTheme.colorScheme.background
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = dateItem.formattedDate, style = MaterialTheme.typography.headlineMedium)
                Text(text = "(${dateItem.date.dayOfWeek})", style = MaterialTheme.typography.bodyMedium)
            }
        }

        // Update the selected date index when the page changes
        selectedDateIndex.value = page
    }
}