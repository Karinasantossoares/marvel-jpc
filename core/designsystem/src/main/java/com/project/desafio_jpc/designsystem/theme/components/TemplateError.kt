package com.project.desafio_jpc.designsystem.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.desafio_jpc.designsystem.R

@Composable
fun TemplateError(
    modifier: Modifier = Modifier,
    isError: Boolean,
    genericError: Boolean,
    content: @Composable () -> Unit

) {
    if (isError) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp),
                painter = painterResource(
                    id =
                    if (genericError) R.drawable.image_generic_error
                    else R.drawable.no_connection
                ),
                contentDescription = "Error",
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(dimensionResource(id = R.dimen.spacing_small)),
                text = if (genericError) stringResource(id = R.string.message_error_generic)
                else stringResource(id = R.string.internet_error),
                textAlign = TextAlign.Center,
                color = Color.Black,

                )
            Spacer(modifier = Modifier.weight(1f))
        }
    } else {
        content()
    }
}