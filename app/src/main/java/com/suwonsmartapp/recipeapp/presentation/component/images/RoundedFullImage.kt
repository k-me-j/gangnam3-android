package com.suwonsmartapp.recipeapp.presentation.component.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.suwonsmartapp.recipeapp.R
import com.suwonsmartapp.recipeapp.ui.AppColors

@Composable
fun RoundedFullImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String = "image",
) {
//    val defaultPainter = painterResource(R.drawable.ic_default_profile)
    val defaultPainter = painterResource(R.drawable.ic_default_profile_man)
    
    AsyncImage(
        model = imageUrl?.ifBlank { null },  // "" 방어
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        placeholder = defaultPainter,
        error = defaultPainter,
        fallback = defaultPainter,  // imageUrl이 null일 때
        modifier = modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = AppColors.secondary40,
            )
    )
}


@Preview(showBackground = true)
@Composable
private fun RoundedFullImagePreview() {
    RoundedFullImage(
        imageUrl = null,
        modifier = Modifier.size(80.dp),
     )
}