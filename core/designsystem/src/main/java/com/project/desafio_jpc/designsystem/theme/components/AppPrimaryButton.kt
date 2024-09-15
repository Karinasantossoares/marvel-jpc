package com.project.desafio_jpc.designsystem.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.desafio_jpc.designsystem.theme.AppTheme

@Composable
fun AppPrimaryButton(modifier: Modifier = Modifier, onClick: () -> Unit, textButton: String) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = textButton, color = MaterialTheme.colorScheme.surface)
    }
}

@Composable
@Preview
fun AppPrimaryButtonPreview() {
    AppTheme {
        AppPrimaryButton(onClick = {}, textButton = "Salvar")
    }

}