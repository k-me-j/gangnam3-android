package com.suwonsmartapp.recipeapp.presentation.screen.search_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchRecipesRoot(  // Stateful 컴포저블 : ViewModel 연결 담당
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchRecipesViewModel = viewModel(
        factory = SearchRecipesViewModel.Factory,
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    SearchRecipesScreen(
        state = state,
        onBackClick = onBackClick,
        onQueryChange = viewModel::onQueryChange,
        onFilterClick = {},
        modifier = modifier,
    )
}
