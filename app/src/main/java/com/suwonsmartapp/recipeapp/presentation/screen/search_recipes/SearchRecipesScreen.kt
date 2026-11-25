package com.suwonsmartapp.recipeapp.presentation.screen.search_recipes

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.R
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe
import com.suwonsmartapp.recipeapp.domain.model.recipe.RecipeCategory
import com.suwonsmartapp.recipeapp.domain.model.user.UserSummary
import com.suwonsmartapp.recipeapp.presentation.component.bars.BackTopBar
import com.suwonsmartapp.recipeapp.presentation.component.bars.SearchBar
import com.suwonsmartapp.recipeapp.presentation.component.cards.RecipeGridList
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

@Composable
fun SearchRecipesScreen(  // Stateless 컴포저블 : UI만 그림, 프리뷰 가능
    state: SearchRecipesState,
    onBackClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()  // 기기 시스템 영역(상태바,노치,하단바,키보드) 여백 먼저 확보
            .padding(
                // 디자인 여백 적용
                vertical = 10.dp,
                horizontal = 30.dp,
            )
            .pointerInput(Unit) {  // 입력 후 빈 배경 터치하면 포커스 해제 및 키보드 내리기
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        // Header
        BackTopBar(
            title = "Search recipes",
            onBackClick = onBackClick,
        )
        
        Spacer(Modifier.size(17.dp))
        
        // 검색창 및 필터
        SearchBar(
            query = state.query,
            onQueryChange = onQueryChange,
            onFilterClick = onFilterClick,
        )
        
        Spacer(Modifier.size(20.dp))
        
        // 결과
        when {
            // 로딩 중
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    CircularProgressIndicator()
                }
            }
            
            else -> {
                RecipeResultSection(
                    title = state.resultTitle,
                    recipes = state.displayedRecipes,
                    isSearching = state.isSearching,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun RecipeResultSection(
    title: String,
    recipes: List<Recipe>,
    isSearching: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        SearchResultHeader(
            title = title,
            isSearching = isSearching,
            resultCount = recipes.size,
        )
        
        RecipeGridList(recipes)
    }
}

@Composable
private fun SearchResultHeader(
    title: String,
    isSearching: Boolean,
    resultCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            color = AppColors.black1,
            style = AppTextStyles.normalTextBold.copy(
                fontWeight = FontWeight.SemiBold,
            )
        )
        
        if (isSearching) {
            Text(
                text = "$resultCount results",
                color = AppColors.gray3,
                style = AppTextStyles.smallerTextRegular,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchRecipesScreenPreview() {
    val chef = UserSummary(
        id = "A001",
        name = "John",
        profileImageUrl = null,
        location = null
    )
    
    val recipe = Recipe(
        id = "001",
        title = "Traditional spare ribs baked Traditional spare ribs baked Traditional spare ribs baked Traditional spare ribs baked ",
        imageUrl = "android.resource://com.suwonsmartapp.recipeapp/${R.drawable.test_img_food01}",
        videoUrl = null,
        chef = chef,
        category = RecipeCategory.UNKNOWN,
        reviewCount = 10,
        cookingTimeMinutes = 20,
        createAt = 261113L,
        rating = 4.0
    )
    
    val recipes = List(10) { index ->
        recipe.copy(
            id = index.toString().padStart(3, '0')
        )
    }
    
    val state = SearchRecipesState(
        recentRecipes = recipes
    )
    
    val state2 = SearchRecipesState(
        query = "ribs",
        searchResults = recipes
    )
    
    SearchRecipesScreen(
//        state = state,  // 검색어 미입력
        state = state2,  // 검색어 입려
        onBackClick = {},
        onQueryChange = {},
        onFilterClick = {},
    )
}