package com.suwonsmartapp.recipeapp.presentation.screen.saved_recipes

import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe

data class SavedRecipesState(
    val recipes: List<Recipe> = emptyList(),
    val bookmarkedIds: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
