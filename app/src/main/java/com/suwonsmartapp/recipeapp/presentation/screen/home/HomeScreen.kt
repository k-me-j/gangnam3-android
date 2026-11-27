package com.suwonsmartapp.recipeapp.presentation.screen.home

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterRecipeCategory
import com.suwonsmartapp.recipeapp.presentation.component.bars.SearchBar
import com.suwonsmartapp.recipeapp.presentation.component.images.RoundedFullImage
import com.suwonsmartapp.recipeapp.presentation.component.tabs.RecipeCategorySelector
import com.suwonsmartapp.recipeapp.presentation.mapper.toStringResId
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

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
        HomeHeader(
            userName = "Kotlin",
            profileImageUrl = "",
        )
        
        Spacer(Modifier.size(30.dp))
        
        // 검색창 및 필터 : TODO: SearchRecipeRoot로 이어지게 해야 함
        SearchBar(
            query = "",
            onQueryChange = {},
            onFilterClick = {},
        )
        
        // 레시피 카테고리 탭
        RecipeCategorySelector(
            items = state.categories,
            selectedItem = state.selectedCategory,
            onItemSelect = onCategoryClick,
            label = { stringResource(it.toStringResId()) },
        )
        
        // 카테고리 별 레시피 카드
        LazyRow() {
        
        }
    }
}

@Composable
private fun HomeHeader(
    userName: String,
    profileImageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String = "profile image",
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        // title
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = "Hello $userName",
                color = AppColors.black1,
                style = AppTextStyles.largeTextBold.copy(
                    fontWeight = FontWeight.SemiBold,
                )
            )
            
            Text(
                text = "What are you cooking today?",
                color = AppColors.gray3,
                style = AppTextStyles.smallerTextRegular,
            )
        }
        
        // profile image
        RoundedFullImage(
            imageUrl = profileImageUrl,
            contentDescription = contentDescription,
            modifier = Modifier.height(IntrinsicSize.Min)
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