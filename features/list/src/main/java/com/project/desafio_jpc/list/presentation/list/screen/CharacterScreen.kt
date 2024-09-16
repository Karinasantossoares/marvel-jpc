package com.project.desafio_jpc.list.presentation.list.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.desafio_jpc.designsystem.R
import com.project.desafio_jpc.designsystem.theme.AppTheme
import com.project.desafio_jpc.designsystem.theme.components.LoadingBuilder
import com.project.desafio_jpc.designsystem.theme.components.TemplateError
import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.presentation.list.components.CardItem
import com.project.desafio_jpc.list.presentation.list.viewmodel.model.ListCharacterState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    state: ListCharacterState,
    lazyListState: LazyListState,
    goToDetail: (idCharacter: String) -> Unit,
    goToProfile: () -> Unit,
) {
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
                        goToProfile()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            tint = MaterialTheme.colorScheme.surface,
                            contentDescription = "perfil"
                        )
                    }
                }
            )
        },
        content = { padding ->
            LoadingBuilder(loading = state.isLoading) {
                TemplateError(
                    isError = state.isError,
                    genericError = state.isGenericError,
                    content = {
                        Column(
                            modifier = Modifier
                                .padding(padding)
                                .background(color = Color.White)
                        ) {
                            LazyColumn(
                                state = lazyListState, modifier = Modifier.testTag("lazy_column")
                            ) {
                                item {
                                    Text(
                                        modifier = Modifier.padding(
                                            start = dimensionResource(id = R.dimen.spacing_medium),
                                            end = dimensionResource(id = R.dimen.spacing_medium),
                                            top = dimensionResource(id = R.dimen.spacing_medium)
                                        ),
                                        text = state.title,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color.Black,
                                        fontSize = 24.sp
                                    )
                                }
                                item {
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = dimensionResource(id = R.dimen.spacing_medium),
                                            vertical = dimensionResource(id = R.dimen.spacing_medium)
                                        ),
                                        text = state.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                }
                                items(count = state.listCharacter.size) { index ->
                                    val character = state.listCharacter[index]
                                    CardItem(
                                        modifier = Modifier
                                            .padding(
                                                start = dimensionResource(id = R.dimen.spacing_medium),
                                                end = dimensionResource(id = R.dimen.spacing_medium),
                                                top = dimensionResource(id = R.dimen.spacing_small),
                                                bottom = dimensionResource(id = R.dimen.spacing_small)
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = Color.Gray,
                                                shape = RoundedCornerShape(20f)
                                            )
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .clickable {
                                                goToDetail(character.id)
                                            },
                                        characterUi = character
                                    )
                                }

                                if (state.isLoadingPagination) {
                                    item {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center,
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.testTag("loading_bottom")
                                            )
                                        }
                                    }
                                }

                                if (state.isErrorWithCache) {
                                    item {
                                        Row(
                                            modifier = Modifier
                                                .padding(vertical = 16.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center,
                                        ) {
                                            Text(text = "Você está sem internet")
                                        }
                                    }
                                }
                            }
                        }
                    })

            }
        }
    )

}

@Preview
@Composable
fun PreviewMyAppBar() {
    AppTheme {
        CharacterScreen(
            lazyListState = rememberLazyListState(),
            state = ListCharacterState(
                title = "Personagens",
                description = "Listagem de personanges da Marvel",
                listCharacter = mutableListOf(
                    CharacterModel(
                        name = "Homem de ferro",
                        id = "1",
                        description = "Descrição detalhada sobre cada personagem",
                    ),
                    CharacterModel(
                        name = "Homem de ferro",
                        id = "1",
                        description = "Descrição detalhada sobre cada personagem",
                    ),
                    CharacterModel(
                        name = "Homem de ferro",
                        id = "1",
                        description = "Descrição detalhada sobre cada personagem",
                    ),
                    CharacterModel(
                        name = "Homem de ferro",
                        id = "1",
                        description = "Descrição detalhada sobre cada personagem",
                    ),
                )
            ),
            goToDetail = { },
            goToProfile = {}
        )
    }
}