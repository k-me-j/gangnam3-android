package com.suwonsmartapp.recipeapp.presentation.mapper

import androidx.annotation.StringRes
import com.suwonsmartapp.recipeapp.R
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterCategory
import com.suwonsmartapp.recipeapp.domain.model.filter.FilterTime

@StringRes  // Int가 R.string.*임을 나타냄
fun FilterTime.toStringResId(): Int = when (this) {
    FilterTime.ALL -> R.string.filter_time_all
    FilterTime.NEWEST -> R.string.filter_time_newest
    FilterTime.OLDEST -> R.string.filter_time_oldest
    FilterTime.POPULARITY -> R.string.filter_time_popularity
}

@StringRes
fun FilterCategory.toStringResId(): Int = when (this) {
    FilterCategory.ALL -> R.string.filter_category_all
    FilterCategory.CEREAL -> R.string.filter_category_cereal
    FilterCategory.VEGETABLES -> R.string.filter_category_vegetables
    FilterCategory.DINNER -> R.string.filter_category_dinner
    FilterCategory.CHINESE -> R.string.filter_category_chinese
    FilterCategory.LOCAL_DISH -> R.string.filter_category_local_dish
    FilterCategory.FRUIT -> R.string.filter_category_fruit
    FilterCategory.BREAKFAST -> R.string.filter_category_breakfast
    FilterCategory.SPANISH -> R.string.filter_category_spanish
    FilterCategory.LUNCH -> R.string.filter_category_lunch
}