package com.example.packagewalk.ui.widgets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.packagewalk.R
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.text.TextCaption
import com.example.packagewalk.ui.widgets.text.TextSubtitle1


/**
 * Строка с отображением какой-то одной конкретной информации с заголовком и значением.
 * @param phone - номер телефона
 */
@Composable
fun RowPhoneInfo(
    phone: String,
    context: Context,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = modifier
                .align(Alignment.CenterStart)
                .clickable {}) {
            TextCaption(stringId = R.string.number_phone, modifier = Modifier.padding(top = 8.dp))
            TextSubtitle1(value = phone, modifier = Modifier.padding(bottom = 8.dp))
            Divider()
        }
        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = dimensionResource(id = R.dimen.around_base))
                .clickable { openDialing(context, phone) })
    }
}

private fun openDialing(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
    startActivity(context, intent, Bundle())
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PackageWalkTheme {
        RowPhoneInfo(phone = "89809216265", context = LocalContext.current)
    }
}