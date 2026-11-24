package com.suwonsmartapp.recipeapp.presentation.component.inputFields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suwonsmartapp.recipeapp.ui.AppColors
import com.suwonsmartapp.recipeapp.ui.AppTextStyles

@Composable
fun SearchInputField(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search recipe",
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = AppColors.gray4,
                modifier = Modifier.size(18.dp),
            )
        },
        textStyle = AppTextStyles.smallerTextRegular.copy(
            color = AppColors.black1,
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = AppColors.gray4,
                style = AppTextStyles.smallerTextRegular,
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppColors.primary80,
            unfocusedBorderColor = AppColors.gray4,
        ),
        modifier = modifier.fillMaxWidth(),
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