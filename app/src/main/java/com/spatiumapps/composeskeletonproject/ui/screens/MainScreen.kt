package com.spatiumapps.composeskeletonproject.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Text(text = "Compose Skeleton Project", modifier.fillMaxSize())
}
