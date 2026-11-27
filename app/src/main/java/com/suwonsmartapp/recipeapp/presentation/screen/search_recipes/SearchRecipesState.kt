package com.suwonsmartapp.recipeapp.presentation.screen.search_recipes

import com.suwonsmartapp.recipeapp.domain.model.filter.FilterRecipeCategory
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe

data class SearchRecipesState(
    val query: String = "",
    
    val recentRecipeIds: List<String> = emptyList(),
    val recentRecipes: List<Recipe> = emptyList(),
    val searchResults: List<Recipe> = emptyList(),
    
    val categories: List<FilterRecipeCategory> = FilterRecipeCategory.entries,
    val selectedRecipeCategory: FilterRecipeCategory = FilterRecipeCategory.ALL,
    
    val filterState: FilterSearchState = FilterSearchState(),
    
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    val isSearching: Boolean
        get() = query.isNotBlank()
    
    val resultTitle: String
        get() = if (query.isBlank()) "Recent Search" else "Search Result"
    
    val displayedRecipes: List<Recipe>
        get() = if (query.isBlank()) recentRecipes else searchResults
}