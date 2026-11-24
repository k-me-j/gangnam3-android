package com.suwonsmartapp.recipeapp.presentation.screen.search_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.suwonsmartapp.recipeapp.RecipeApplication
import com.suwonsmartapp.recipeapp.data.repository.RecipeRepository
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchRecipesViewModel(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SearchRecipesState())
    val state = _state.asStateFlow()
    
    private var allRecipes: List<Recipe> = emptyList()
    private var recipeMap: Map<String, Recipe> = emptyMap()
    private var recentRecipeIds: List<String> = emptyList()
    
    init {
        loadRecipes()
    }
    
    private fun loadRecipes() {
        viewModelScope.launch {   // getRecipes()가 suspend라서 코루틴 열어주기
            _state.update { currentState ->
                currentState.copy(  // copy로 동시성 문제 예방
                    isLoading = true,
                )
            }
            
            try {
                // 1. 데이터 먼저 비동기로 가져오고
                val loadRecipes = repository.getRecipes()
                allRecipes = loadRecipes
                recipeMap = allRecipes.associateBy { it.id }  // key:value인 맵
                // allRecipes.find { it.id == id } 이러면 매번 리스트를 다 뒤져야 해서 느림. 맵으로 찾기할 거임
                recentRecipeIds = loadRecipes.map { it.id }  // TODO : 임시로 다 갖고옴
                
                // 2. 상태 업데이트
                // : 상태 업데이트하는 것 안에서 비동기로 가져오는 것은 지양(update 내부에서 while 돈다)
                _state.update { currentState ->
                    currentState.copy(
                        recentRecipeIds = recentRecipeIds,  // TODO : 임시로 다 넣었다
                        recentRecipes = toRecipes(recentRecipeIds),
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
    
    fun onQueryChange(query: String) {
        _state.update { currentState ->
            currentState.copy(
                query = query,
                
            )
        }
    }
    
    private fun toRecipes(ids: List<String>): List<Recipe> = ids.mapNotNull { recipeMap[it] }
    // mapNotNull : 레시피가 없어서 null 반환되어도 에러 내거나 null 안넣고 건너뜀
    // filter { it.id in ids } 쓰면 allRecipes의 순서로 나옴. 최근 검색 순서가 아님.
    
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val recipeRepository: RecipeRepository = (
                        this[APPLICATION_KEY] as RecipeApplication
                        ).recipeRepository
                
                SearchRecipesViewModel(
                    repository = recipeRepository,
                )
            }
        }
    }
}