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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe
import com.suwonsmartapp.recipeapp.presentation.component.buttons.TuneButton
import com.suwonsmartapp.recipeapp.presentation.component.inputFields.SearchInputField
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
        SearchRecipesHeader(
            title = "Search recipes",
            onBackClick = onBackClick,
        )
        
        Spacer(Modifier.size(17.dp))
        
        // 검색창 및 필터
        SearchBarSection(
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
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun SearchRecipesHeader(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(  // 아이콘과 텍스트를 독립적으로 정렬하기 위해 row 대신 box 사용
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        // 뒤로가기
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,  // 언어 읽기 방향에 따라 back 방향 자동 적용
                contentDescription = "Back",
                tint = AppColors.black4,
            )
        }
        
        // Title
        Text(
            text = title,
            color = AppColors.black3,
            style = AppTextStyles.mediumTextBold.copy(
                fontWeight = FontWeight.SemiBold,
            )
        )
    }
}

@Composable
private fun SearchBarSection(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        // 검색창
        SearchInputField(
            text = query,
            onValueChange = onQueryChange,
            placeholder = "Search recipe",
            modifier = Modifier.weight(1f)
        )
        
        // 필터 버튼
        TuneButton(
            onClick = onFilterClick,
        )
    }
}

@Composable
private fun RecipeResultSection(
    title: String,
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchRecipesScreenPreview() {
//    SearchRecipesHeader(
//        title = "hello",
//        onBackClick = {}
//    )
    val state = SearchRecipesState()
    
    SearchRecipesScreen(
        state = state,
        onBackClick = {},
        onQueryChange = {},
        onFilterClick = {},
    )
}