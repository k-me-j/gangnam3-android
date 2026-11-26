package com.suwonsmartapp.recipeapp.presentation.screen.home

import com.suwonsmartapp.recipeapp.domain.model.filter.FilterRecipeCategory
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe

data class HomeState(
    val userName: String = "",
    val profileImageUrl: String? = null,
    
    val categories: List<FilterRecipeCategory> = FilterRecipeCategory.entries,
    val selectedCategory: FilterRecipeCategory = FilterRecipeCategory.ALL,
    val filteredRecipes: List<Recipe> = emptyList(),
    
    val bookmarkedRecipeIds: Set<String> = emptySet(),
    
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)