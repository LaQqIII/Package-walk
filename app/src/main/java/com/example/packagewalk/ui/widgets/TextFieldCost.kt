package com.example.packagewalk.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.packagewalk.R
import com.example.packagewalk.ui.theme.PackageWalkTheme
import com.example.packagewalk.ui.widgets.text.TextSubtitle1

@Composable
fun TextFieldCost(
    value: String,
    onClickAddButton: () -> Unit,
    onClickRemoveButton: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        TextSubtitle1(
            value = stringResource(id = R.string.enter_cost),
            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.around_base))
        )
        IconButton(onClick = { onClickRemoveButton() }) {
            Icon(imageVector = Icons.Default.RemoveCircleOutline, contentDescription = "")
        }
        TextSubtitle1(value = value)
        IconButton(onClick = { onClickAddButton() }) {
            Icon(imageVector = Icons.Default.AddCircleOutline, contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    PackageWalkTheme {
        TextFieldCost(value = "100", onClickAddButton = { /*TODO*/ }) {

        }
    }
}

