package com.example.packagewalk.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.packagewalk.R

/**
 * Отрисовывает верхнюю панель приложения
 * @param titleId id строки из ресурсов, которая будет выведена в панели
 * @param onClickIcon действие по нажатию на иконку
 */
@Composable
fun PackageWalkTopBar(
    @StringRes titleId: Int? = null,
    hasBackArrow: Boolean = false,
    onClickIcon: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_top_bar)),
        contentAlignment = Alignment.CenterStart
    ) {
        if (hasBackArrow) {
            IconButton(onClick = { onClickIcon() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
        titleId?.let {
            TextH6(
                stringId = it,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}