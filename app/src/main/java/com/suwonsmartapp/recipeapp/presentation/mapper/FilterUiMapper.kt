package com.suwonsmartapp.recipeapp.presentation.mapper

import androidx.annotation.StringRes
import com.suwonsmartapp.recipeapp.R
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterRecipeCategory
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterTime

@StringRes  // Int가 R.string.*임을 나타냄
fun FilterTime.toStringResId(): Int = when (this) {
    FilterTime.ALL -> R.string.filter_time_all
    FilterTime.NEWEST -> R.string.filter_time_newest
    FilterTime.OLDEST -> R.string.filter_time_oldest
    FilterTime.POPULARITY -> R.string.filter_time_popularity
}

@StringRes
fun FilterRecipeCategory.toStringResId(): Int = when (this) {
    FilterRecipeCategory.ALL -> R.string.filter_category_all
    FilterRecipeCategory.CEREAL -> R.string.filter_category_cereal
    FilterRecipeCategory.VEGETABLES -> R.string.filter_category_vegetables
    FilterRecipeCategory.DINNER -> R.string.filter_category_dinner
    FilterRecipeCategory.CHINESE -> R.string.filter_category_chinese
    FilterRecipeCategory.LOCAL_DISH -> R.string.filter_category_local_dish
    FilterRecipeCategory.FRUIT -> R.string.filter_category_fruit
    FilterRecipeCategory.BREAKFAST -> R.string.filter_category_breakfast
    FilterRecipeCategory.SPANISH -> R.string.filter_category_spanish
    FilterRecipeCategory.LUNCH -> R.string.filter_category_lunch
}