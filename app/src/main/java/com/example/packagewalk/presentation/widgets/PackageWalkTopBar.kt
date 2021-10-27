package com.example.packagewalk.presentation.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.example.packagewalk.R

/**
 * Отрисовывает верхнюю панель приложения
 * @param titleId id строки из ресурсов, которая будет выведена в панели
 * @param icon иконка, для отображения слева
 * @param onClickIcon действие по нажатию на иконку
 */
@Composable
fun PackageWalkTopBar(@StringRes titleId: Int?, icon: ImageVector?, onClickIcon: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.height_top_bar)),
        contentAlignment = Alignment.CenterStart
    ) {
        icon?.let {
            IconButton(onClick = { onClickIcon() }) {
                Icon(imageVector = it, contentDescription = "")
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