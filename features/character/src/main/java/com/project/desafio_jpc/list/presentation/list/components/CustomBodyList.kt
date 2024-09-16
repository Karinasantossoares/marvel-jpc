package com.project.desafio_jpc.list.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.desafio_jpc.designsystem.R
import com.project.desafio_jpc.designsystem.theme.AppTheme
import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.presentation.list.viewmodel.model.ListCharacterState

@Composable
fun CustomBodyList(
    modifier: Modifier = Modifier,
    listCharacterState: ListCharacterState
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth()
        ) {
            CharacterSearchBar()

        }

        Text(
            text = listCharacterState.title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Text(
            text = listCharacterState.description,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = dimensionResource(id = R.dimen.spacing_medium)
            )
        )
        listCharacterState.listCharacter.forEach {
            CardItem(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                    .border(
                        width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20f)
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable {
                        //click
                    },
                characterUi = it
            )
        }
    }
}

@Composable
@Preview
fun CustomBodyListPreview() {
    AppTheme {
        CustomBodyList(
            listCharacterState = ListCharacterState(
                title = "Personagens",
                description = "Lista de personagens da Marvel",
                listCharacter = mutableListOf(
                    CharacterModel(
                        name = "Mulher Marvel",
                        description = "Descrição detalhada sobre cada personagem conforme retorno da minha api",
                        id = "2",
                    ),
                    CharacterModel(
                        name = "Mulher Maravilha",
                        description = "Descrição detalhada sobre cada personagem conforme retorno da minha api",
                        id = "2",

                        ),
                    CharacterModel(
                        name = "Mulher Hulk",
                        description = "Descrição detalhada sobre cada personagem conforme retorno da minha api",
                        id = "2",
                    ),
                )
            )
        )
    }
}