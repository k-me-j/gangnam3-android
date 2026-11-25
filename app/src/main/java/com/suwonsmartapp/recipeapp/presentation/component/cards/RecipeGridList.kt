package com.suwonsmartapp.recipeapp.presentation.component.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.R
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe
import com.suwonsmartapp.recipeapp.domain.model.recipe.RecipeCategory
import com.suwonsmartapp.recipeapp.domain.model.user.UserSummary

@Composable
fun RecipeGridList(
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // 열 개수
        horizontalArrangement = Arrangement.spacedBy(15.dp),  // 좌우 간격
        verticalArrangement = Arrangement.spacedBy(15.dp),  // 상하 간격
    ) {
        items(recipes) { recipe ->
            RecipeGridCard(recipe)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RecipeGridListPreview() {
    val chef = UserSummary(
        id = "A001",
        name = "John",
        profileImageUrl = null,
        location = null
    )
    
    val recipe = Recipe(
        id = "001",
        title = "Traditional spare ribs baked Traditional spare ribs baked Traditional spare ribs baked Traditional spare ribs baked ",
        imageUrl = "android.resource://com.suwonsmartapp.recipeapp/${R.drawable.test_img_food01}",
        videoUrl = null,
        chef = chef,
        category = RecipeCategory.UNKNOWN,
        reviewCount = 10,
        cookingTimeMinutes = 20,
        createAt = 261113L,
        rating = 4.0
    )
    
    val recipes = List(10) { index ->
        recipe.copy(
            id = index.toString().padStart(3, '0')
        )
    }
    
    RecipeGridList(recipes)
}