package com.project.desafio_jpc.list.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.project.desafio_jpc.designsystem.R
import com.project.desafio_jpc.designsystem.theme.AppTheme
import com.project.desafio_jpc.list.domain.model.CharacterModel


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    characterUi: CharacterModel
) {
    Row(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface
            )
            .padding(dimensionResource(id = R.dimen.spacing_medium))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = characterUi.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.spacing_medium),
                    top = dimensionResource(id = R.dimen.spacing_medium)
                )
            )
            Text(
                text = characterUi.description.let { it.ifEmpty { "Sem descrição" } },
                maxLines = 2,
                fontSize = 12.sp,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.spacing_medium),
                    top = 8.dp
                )
            )

        }
        Image(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_medium))
                .size(60.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically)
                .border(
                    width = dimensionResource(id = R.dimen.spacing_2),
                    color = MaterialTheme.colorScheme.onTertiary,
                    shape = CircleShape
                ),
            painter = rememberAsyncImagePainter(characterUi.thumbnail.getUrlImage()),
            contentDescription = "imagem do personagem",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
@Preview
fun CustomCardItemPreview() {
    AppTheme {
        CardItem(
            characterUi = CharacterModel(
                name = "Hulk",
                description = "O vingador mais forte",
                id = "2",
            )
        )
    }
}