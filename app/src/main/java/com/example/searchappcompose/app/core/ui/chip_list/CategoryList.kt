package com.example.searchappcompose.app.core.ui.chip_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.searchappcompose.app.core.models.SearchCategory

@Composable
fun CategoryList(
    content: List<SearchCategory>?,
    onSearchCategoryClick: (SearchCategory) -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(8.dp)
    ) {
        content?.forEach {
            CategoryChip(category = it, onSearchCategoryClick = onSearchCategoryClick)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun CategoryChip(
    category: SearchCategory,
    onSearchCategoryClick: (SearchCategory) -> Unit
) {
    Surface(
        color = if (category.isSelected) Color.Gray else MaterialTheme.colorScheme.secondary,
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = category.isSelected,
                    onValueChange = {}
                )
                .fillMaxWidth()
                .clickable {
                    onSearchCategoryClick.invoke(category)
                },
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = category.categoryName,
                modifier = Modifier.padding(8.dp),
                color = if (category.isSelected) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}