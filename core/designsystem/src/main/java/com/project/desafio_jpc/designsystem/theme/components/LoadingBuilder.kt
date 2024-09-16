package com.project.desafio_jpc.designsystem.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag

@Composable
fun LoadingBuilder(
    modifier: Modifier = Modifier,
    loading: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(if (loading) Color.Gray.copy(alpha = 0.3f) else Color.Transparent)
            .fillMaxSize()
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .testTag("loading_center")
                    .align(Alignment.Center)
            )
        } else {
            content()
        }
    }

}