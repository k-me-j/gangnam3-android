package com.suwonsmartapp.recipeapp.presentation.component.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.presentation.component.buttons.TuneButton
import com.suwonsmartapp.recipeapp.presentation.component.inputFields.SearchInputField

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search recipe",
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        // 검색창
        SearchInputField(
            text = query,
            onValueChange = onQueryChange,
            placeholder = placeholder,
            modifier = Modifier.weight(1f)
        )
        
        // 필터 버튼
        TuneButton(
            onClick = onFilterClick,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    
    SearchBar(
        query = query,
        onQueryChange = {
            query = it
        },
        onFilterClick = {},
    )
}