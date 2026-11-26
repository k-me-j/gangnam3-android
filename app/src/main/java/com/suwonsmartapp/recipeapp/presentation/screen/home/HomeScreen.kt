package com.suwonsmartapp.recipeapp.presentation.screen.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterRecipeCategory
import com.suwonsmartapp.recipeapp.presentation.component.bars.SearchBar
import com.suwonsmartapp.recipeapp.presentation.component.tabs.RecipeCategorySelector
import com.suwonsmartapp.recipeapp.presentation.mapper.toStringResId

@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier
) {
//    HomeScreen(
//        state = TODO(),
//        onCategoryClick = TODO(),  viewModel::onCategoryClick,
//        modifier = TODO()
//    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    onCategoryClick: (FilterRecipeCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    
    // FilterRecipeCategory 목록이나 기기 언어가 바뀔 때만 재생성, 일반 리컴포지션 때는 재사용
    // stringResource()에 LocalConfiguration 변경 감지 및 리소스 캐싱 최적화 되어 있음
    val categoryLabels: List<String> = state.categories.map {
        stringResource(it.toStringResId())
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()  // 기기 시스템 영역(상태바,노치,하단바,키보드) 여백 먼저 확보
            .padding(
                vertical = 20.dp,
                horizontal = 30.dp,
            )
            .pointerInput(Unit) {  // 입력 후 빈 배경 터치하면 포커스 해제
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
    ) {
        // Header
        
        
        // 검색창 및 필터 : TODO: SearchRecipeRoot로 이어지게 해야 함
        SearchBar(
            query = "",
            onQueryChange = {},
            onFilterClick = {},
        )
        
        // 레시피 카테고리 탭
        RecipeCategorySelector(
            labels = categoryLabels,
            selectedIndex = state.categories.indexOf(state.selectedCategory),
            onValueChange = { index ->
                onCategoryClick(state.categories[index])
                // index로 접근하면 목록 순서도 신경써야 하기 떄문에 카테고리를 넘기는 게 맞다
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    var state by remember { mutableStateOf(HomeState()) }
    
    HomeScreen(
        state = state,
        onCategoryClick = { category ->
            state = state.copy(
                selectedCategory = category
            )
        },
    )
}