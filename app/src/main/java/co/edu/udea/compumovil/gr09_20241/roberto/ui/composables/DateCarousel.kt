package co.edu.udea.compumovil.gr09_20241.roberto.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20241.roberto.ui.DateItem
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DateCarousel(dateItems: List<DateItem>) {
    val pagerState = rememberPagerState(
        initialPage = dateItems.indexOfFirst { it.date == LocalDate.now() },
        initialPageOffsetFraction = 0f
    ) {
        dateItems.size
    }
    val selectedDateIndex = remember { mutableStateOf(pagerState.currentPage) }

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
                .height(itemSize)
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

        selectedDateIndex.value = page
    }
}