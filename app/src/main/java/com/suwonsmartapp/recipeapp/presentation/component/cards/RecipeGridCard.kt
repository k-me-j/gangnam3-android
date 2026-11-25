package com.suwonsmartapp.recipeapp.presentation.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.suwonsmartapp.recipeapp.R
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe
import com.suwonsmartapp.recipeapp.domain.model.recipe.RecipeCategory
import com.suwonsmartapp.recipeapp.domain.model.user.UserSummary
import com.suwonsmartapp.recipeapp.presentation.component.labels.RatingBadge
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

@Composable
fun RecipeGridCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)  // 결정된 가로 너비에 비례해서 1f(1:1) 비율 자동 계산
            .clip(RoundedCornerShape(10.dp))
    ) {
        // 배경 이미지
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_image_placeholder),
            error = painterResource(R.drawable.ic_image_placeholder),
            modifier = Modifier.fillMaxSize(),
        )
        
        // 이미지 위 선형 그라데이션 오버레이
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            AppColors.black1.copy(alpha = 0f),
                            AppColors.black1
                        )
                    )
                )
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            // 별점
            RatingBadge(
                rating = recipe.rating,
                modifier = Modifier.align(Alignment.End)
            )
            
            Spacer(Modifier.weight(1f))
            
            // 제목
            Text(
                text = recipe.title,
                color = AppColors.white,
                style = AppTextStyles.smallerTextBold.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,  // ...으로 생략
            )
            
            Spacer(Modifier.size(3.dp))
            
            // 작성자 정보
            Text(
                text = "By ${recipe.chef.name}",
                color = AppColors.gray3,
                style = AppTextStyles.smallLabelRegular,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RecipeGridCardPreview() {
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
    
    RecipeGridCard(
        recipe = recipe,
    )
}