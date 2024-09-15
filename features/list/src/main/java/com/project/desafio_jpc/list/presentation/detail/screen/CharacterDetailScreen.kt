package com.project.desafio_jpc.list.presentation.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.project.desafio_jpc.designsystem.R
import com.project.desafio_jpc.designsystem.theme.AppTheme
import com.project.desafio_jpc.designsystem.theme.components.AppPrimaryButton
import com.project.desafio_jpc.designsystem.theme.components.TemplateError
import com.project.desafio_jpc.list.presentation.detail.viewmodel.model.CharacterDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(state: CharacterDetailState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tab_bar_title),
                        color = MaterialTheme.colorScheme.surface
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            tint = MaterialTheme.colorScheme.surface,
                            contentDescription = stringResource(R.string.tab_bar_title)
                        )
                    }
                }
            )
        },
        content = { padding ->
            TemplateError(
                isError = state.isError,
                genericError = state.isGenericError
            ) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .background(color = Color.White)
                ) {
                    Row {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(200.dp)
                                .clip(RectangleShape)
                                .align(Alignment.CenterVertically)
                                .border(
                                    width = 2.dp,
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    shape = RectangleShape
                                ),
                            painter = rememberAsyncImagePainter(state.urlImage),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                        Text(
                            modifier = Modifier.padding(bottom = 16.dp),
                            text = state.description,
                            color = MaterialTheme.colorScheme.outline
                        )
                        LazyColumn {
                            items(count = state.listComics.size) { index ->
                                val item = state.listComics[index]
                                Text(
                                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.spacing_medium)),
                                    text = item
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        AppPrimaryButton(onClick = { /*TODO*/ }, textButton = state.textButton)
                    }
                }
            }
        }
    )
}

@Composable
@Preview
fun DetailScreenPreview() {
    AppTheme {
        CharacterDetailScreen(
            CharacterDetailState(
                textButton = "Salvar",
                title = "Homem de ferro",
                description = "O mais rico",
            )
        )
    }

}