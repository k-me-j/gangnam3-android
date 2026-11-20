package com.suwonsmartapp.recipeapp.presentation.screen.saved_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.suwonsmartapp.recipeapp.RecipeApplication
import com.suwonsmartapp.recipeapp.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SavedRecipesState())
    val state = _state.asStateFlow()
    
    init {
        loadSavedRecipes()
    }
    
    private fun loadSavedRecipes() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(  // copy로 동시성 문제 예방
                    isLoading = true,
                )
            }
            
            try {
                // 데이터를 먼저 비동기로 가져온 뒤
                val loadedRecipes = repository.getRecipes()
                
                // 상태 업데이트
                // : 상태 업데이트하는 것 안에서 비동기로 가져오는 것은 지양(update 내부에서 while 돈다)
                _state.update { currentState ->
                    currentState.copy(
                        recipes = loadedRecipes,
                        isLoading = false,
                    )
                }
            } catch (e: Exception) {
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to load recipes",
                    )
                }
            }
        }
    }
    
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val recipeRepository: RecipeRepository = (
                        this[APPLICATION_KEY] as RecipeApplication
                        ).recipeRepository
                
                SavedRecipesViewModel(
                    repository = recipeRepository
                )
            }
        }
    }
}