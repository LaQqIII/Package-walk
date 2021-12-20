package com.example.packagewalk.ui.screens.new_deal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.packagewalk.R
import com.example.packagewalk.ui.screens.authorization.AuthorizationUI
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.TextFieldApp

@Preview(showBackground = true)
@Composable
fun NewDealUI() {
    //AuthorizationUI {
    val from = remember { mutableStateOf("") }
    val to = remember { mutableStateOf("") }
    Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.new_order) }) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (info, create) = createRefs()
            Column(modifier = Modifier
                .constrainAs(info) {
                    top.linkTo(parent.top)
                }
                .padding(dimensionResource(id = R.dimen.around_base))) {
                TextFieldApp(
                    value = from.value,
                    onValueChange = { from.value = it },
                    onDoneClick = { /*TODO*/ },
                    label = R.string.from,
                    modifier = Modifier.fillMaxWidth()
                )
                TextFieldApp(
                    value = to.value,
                    onValueChange = { to.value = it },
                    onDoneClick = { /*TODO*/ },
                    label = R.string.to,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            PackageWalkButton(
                stringId = R.string.create_order,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .constrainAs(create) { bottom.linkTo(parent.bottom) }
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.around_base))
            )
        }
    }
    //}
}