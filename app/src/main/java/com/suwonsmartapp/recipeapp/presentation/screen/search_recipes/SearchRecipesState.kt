package com.suwonsmartapp.recipeapp.presentation.screen.search_recipes

import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe

data class SearchRecipesState(
    val query: String = "",
    val recentRecipeIds: List<String> = emptyList(),
    val recentRecipes: List<Recipe> = emptyList(),
    val searchResults: List<Recipe> = emptyList(),
    
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    val resultTitle: String
        get() = if (query.isBlank()) "Recent Search" else "Search Result"
    
    val displayedRecipes: List<Recipe>
        get() = if (query.isBlank()) recentRecipes else searchResults
}