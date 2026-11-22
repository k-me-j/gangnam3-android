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
                    isLoading = true,  // 로딩 중으로 시작
                )
            }
            
            try {
                // 1. 데이터를 먼저 비동기로 가져온 뒤
                val loadedRecipes = repository.getRecipes()
                
                // 2. 상태 업데이트
                // : 상태 업데이트하는 것 안에서 비동기로 가져오는 것은 지양(update 내부에서 while 돈다)
                _state.update { currentState ->
                    currentState.copy(  // copy로 동시성 문제 예방
                        recipes = loadedRecipes,
                        bookmarkedIds = loadedRecipes.map { it.id }.toSet(),
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
    
    fun toggleBookmark(recipeId: String) {
        _state.update { currentState ->
            val currentIds = currentState.bookmarkedIds
            
            currentState.copy(  // 동시성 문제 예방
                bookmarkedIds = if (recipeId in currentIds) {
                    currentIds - recipeId
                } else {
                    currentIds + recipeId
                }
            )
            // set에서 -, +를 쓰면 기존 set 그대로 두고 새 set을 만듦. 그래서 StateFlow가 값이 바뀐걸 감지하고 화면을 다시 그림.
            // MutableSet의 add, remove로 기존 set을 직접 변경하면 같은 객체라서 화면이 갱신되지 않을 수 있다!
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