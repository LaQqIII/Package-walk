package com.example.packagewalk.ui.screens.profile

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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.packagewalk.R
import com.example.packagewalk.ui.screens.authorization.AuthorizationUI
import com.example.packagewalk.ui.widgets.PackageWalkButton
import com.example.packagewalk.ui.widgets.PackageWalkTopBar
import com.example.packagewalk.ui.widgets.TextFieldApp

@Preview(showBackground = true)
@Composable
fun ProfileUI() {
    val name = remember { mutableStateOf("") }
    AuthorizationUI {
        Scaffold(topBar = { PackageWalkTopBar(titleId = R.string.screen_profile) }) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.around_base))
            ) {
                val (nameRef, exitRef) = createRefs()
                Column(modifier = Modifier.constrainAs(nameRef) {
                    top.linkTo(parent.top)
                }) {
                    TextFieldApp(
                        value = name.value,
                        onValueChange = { name.value = it },
                        onDoneClick = { /*TODO*/ },
                        label = R.string.what_your_name,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "+79809216265",
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.around_base))
                    )
                }
                PackageWalkButton(
                    stringId = R.string.logout,
                    onClick = { /*TODO*/ },
                    modifier = Modifier.constrainAs(exitRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(
                            anchor = parent.bottom,
                            margin = 16.dp
                        )
                    }
                )
            }
        }
    }
}