package com.suwonsmartapp.recipeapp.presentation.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.suwonsmartapp.recipeapp.R
import com.suwonsmartapp.recipeapp.domain.model.recipe.Recipe
import com.suwonsmartapp.recipeapp.domain.model.recipe.RecipeCategory
import com.suwonsmartapp.recipeapp.domain.model.user.UserSummary
import com.suwonsmartapp.recipeapp.presentation.component.buttons.CircleBookmarkButton
import com.suwonsmartapp.recipeapp.presentation.component.labels.RatingBadge
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

@Composable
fun DishCard(
    recipe: Recipe,
    isBookmarked: Boolean,
    onCardClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(231.dp)
            .clickable { onCardClick() },
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(176.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    color = AppColors.gray4.copy(
                        alpha = 0.5f,
                    )
                )
                .padding(10.dp),
        ) {
            Spacer(Modifier.height(56.dp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = recipe.title,
                    color = AppColors.gray1,
                    style = AppTextStyles.smallTextBold.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )
            }
            
            Spacer(Modifier.height(19.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    Text(
                        text = "Time",
                        color = AppColors.gray3,
                        style = AppTextStyles.smallerTextRegular,
                    )
                    
                    Text(
                        text = "${recipe.cookingTimeMinutes} Mins",
                        color = AppColors.gray1,
                        style = AppTextStyles.smallerTextBold.copy(
                            fontWeight = FontWeight.SemiBold,
                        )
                    )
                }
                
                CircleBookmarkButton(
                    isBookmarked = isBookmarked,
                    onBookmarkClick = onBookmarkClick,
                )
            }
        }
    
        Box (
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(110.dp)
        ) {
            // 이미지
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_image_placeholder),
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center)
            )
            
            // 별점
            RatingBadge(
                rating = recipe.rating,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 30.dp,
                    )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DishCardPreview() {
    val chef = UserSummary(
        id = "A001",
        name = "John",
        profileImageUrl = null,
        location = null
    )
    
    val recipe = Recipe(
        id = "001",
        title = "Traditional",
//        title = "Traditional spare ribs baked Traditional spare ribs baked Traditional spare ribs baked Traditional spare ribs baked ",
        imageUrl = "android.resource://com.suwonsmartapp.recipeapp/${R.drawable.test_img_food01}",
//        imageUrl = R.drawable.test_img_food02,
        videoUrl = null,
        chef = chef,
        category = RecipeCategory.UNKNOWN,
        reviewCount = 10,
        cookingTimeMinutes = 20,
        createAt = 261113L,
        rating = 4.0
    )
    
    DishCard(
        recipe = recipe,
        onCardClick = {},
        isBookmarked = true,
        onBookmarkClick = {},
    )
}