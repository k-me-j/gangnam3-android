package com.suwonsmartapp.recipeapp.presentation.screen.search_recipes

import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe

data class SearchRecipesState(
    val query: String = "",
    val recentRecipes: List<Recipe> = emptyList(),
    val searchResults: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
)