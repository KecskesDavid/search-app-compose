package com.example.searchappcompose.app.chip_list

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.searchappcompose.app.model.SearchCategory

@Composable
@Preview
fun CategoryListPreview() {
    val searchList = mutableListOf(
        SearchCategory("Politics", iconId = null),
        SearchCategory("Sport", iconId = null, isSelected = true),
        SearchCategory("Sport", iconId = null),
        SearchCategory("Sport", iconId = null),
        SearchCategory("Sport", iconId = null),
    )

    CategoryList(content = searchList, {})
}

@Composable
fun CategoryList(
    content: List<SearchCategory>,
    onSearchClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier.horizontalScroll(scrollState)
    ) {
        content.forEach {
            CategoryChip(category = it, onSearchClick)
        }
    }
}

@Composable
fun CategoryChip(
    category: SearchCategory,
    onSearchClick: () -> Unit
) {
    Surface(
        color = if (category.isSelected) Color.Gray else MaterialTheme.colorScheme.secondary,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = category.isSelected,
                    onValueChange = {}
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = category.categoryName,
                modifier = Modifier.padding(8.dp),
//                color = if(category.isSelected) Color.Black else Color.White,
                color = Color.White
            )
        }
    }
}