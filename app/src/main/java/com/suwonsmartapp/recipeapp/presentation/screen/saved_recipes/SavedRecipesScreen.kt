package com.suwonsmartapp.recipeapp.presentation.screen.saved_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suwonsmartapp.recipeapp.presentation.component.cards.RecipeCard
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.Factory,
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()  // 기기 시스템 영역 여백 먼저 확보
            .padding(  // 디자인 여백 적용
                vertical = 10.dp,
                horizontal = 30.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
        )
    ){
        Text(
            text = "Saved recipes",
            color = AppColors.black2,
            style = AppTextStyles.mediumTextBold.copy(
                fontWeight = FontWeight.SemiBold,
            )
        )
        
        when {  // if 대신 when 사용하면 상태 추가 시 확장에 용이함
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),  // 이것과 함께 쓸 때는 fillMaxWidth()가 정확한 표현. weight가 세로 크기를 결정하니까.
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            
            state.errorMessage != null -> {
                state.errorMessage?.let { errorMsg ->  // state라서 !null로 들어왔어도 값이 변했을 수 있기 때문에 한 번 더 체크
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = errorMsg,
                        )
                    }
                }
            }
            
            else -> {
//                val bookmarkedRecipes = state.recipes.filter { it.id in state.bookmarkedIds }  // 북마크 해제하자마자 지우기
                val bookmarkedRecipes = state.recipes  // 북마크 해제해도 우선 화면에 놔두기
                
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    items(
                        items = bookmarkedRecipes,
                        key = { it.id },
                    ) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            isBookmarked = recipe.id in state.bookmarkedIds,
                            onBookmarkClick = { viewModel.toggleBookmark(recipe.id) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SavedRecipesScreenPreview() {
    SavedRecipesScreen()
}