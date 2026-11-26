package com.suwonsmartapp.recipeapp.presentation.screen.home

import androidx.lifecycle.ViewModel
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
    
    fun onCategorySelect(index: Int) {
        val selected = _state.value.categories.getOrNull(index) ?: return
        if (_state.value.selectedCategory == selected) return
        
        _state.update {
            it.copy(
                selectedCategory = selected,
            )
        }
    }
}