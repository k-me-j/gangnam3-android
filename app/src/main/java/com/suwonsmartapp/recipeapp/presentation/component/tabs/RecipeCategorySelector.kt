package com.suwonsmartapp.recipeapp.presentation.component.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterRecipeCategory
import com.suwonsmartapp.recipeapp.presentation.mapper.toStringResId
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

@Composable
fun <T> RecipeCategorySelector(
    items: List<T>,
    selectedItem: T,
    onItemSelect: (T) -> Unit,
    label: @Composable (T) -> String,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 10.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(items) { item ->
            val isSelected = item == selectedItem
            val backgroundColor = if (isSelected) AppColors.primary100 else AppColors.white
            val textColor = if (isSelected) AppColors.white else AppColors.primary80
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        color = backgroundColor,
                    )
                    .clickable {
                        onItemSelect(item)
                    }
                    .padding(
                        horizontal = 20.dp,
                        vertical = 7.dp,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = label(item),
                    color = textColor,
                    style = AppTextStyles.smallerTextBold.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RecipeCategorySelectorPreview() {
    val categories = FilterRecipeCategory.entries
    var selectedCategory by remember { mutableStateOf(categories.first()) }
    
    RecipeCategorySelector(
        items = categories,
        selectedItem = selectedCategory,
        onItemSelect = { selectedCategory = it },
        label = { stringResource(it.toStringResId()) },
    )
}