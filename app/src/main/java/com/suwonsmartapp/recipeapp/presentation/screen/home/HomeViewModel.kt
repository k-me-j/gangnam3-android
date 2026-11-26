package com.suwonsmartapp.recipeapp.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterRecipeCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(

) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    
    init {
        loadHomeData()
    }
    
    private fun loadHomeData() {
    
    }
    
    fun onCategoryClick(category: FilterRecipeCategory) {
        if (_state.value.selectedCategory == category) return
        
        _state.update {
            it.copy(
                selectedCategory = category,
            )
        }
    }
}