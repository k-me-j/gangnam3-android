package com.suwonsmartapp.recipeapp.presentation.component.inputFields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

@Composable
fun SearchInputField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search recipe",
) {
    var isFocused by remember { mutableStateOf(false) }
    val borderColor = if (isFocused) AppColors.primary80 else AppColors.gray4
    
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = AppTextStyles.smallerTextRegular.copy(
            color = AppColors.black1,
        ),
        cursorBrush = SolidColor(AppColors.gray1),
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
        },
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
//                    alignment = Alignment.CenterHorizontally,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = (1.3).dp,
                        color = borderColor,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(
                        vertical = 11.dp,
                        horizontal = 10.dp,
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = AppColors.gray4,
                    modifier = Modifier.size(18.dp),
                )
                
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = AppColors.gray4,
                            style = AppTextStyles.smallerTextRegular,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchInputFieldPreview() {
    var text by remember { mutableStateOf("") }
    
    SearchInputField(
        text = text,
        onValueChange = { text = it },
        placeholder = "Search Recipe",
    )
}