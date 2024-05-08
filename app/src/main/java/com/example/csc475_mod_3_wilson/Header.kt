package com.example.csc475_mod_3_wilson

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun HeaderView(
    title: String,
    whenClicked: () -> Unit = {}) {
    val backIcon: (@Composable () -> Unit)? =
        if (!title.contains("To Do List")) {
            {
                IconButton(onClick = { whenClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.TwoTone.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }

    TopAppBar(
        title = {
            Text(text = title,
                color = colorResource(id = R.color.yellow),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .heightIn(32.dp))},
                elevation = 5.dp,
                navigationIcon = backIcon,
                backgroundColor = colorResource(id = R.color.custom_purple))
        }
