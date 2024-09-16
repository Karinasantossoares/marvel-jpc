package com.project.desafio_jpc.list.character

import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.presentation.list.viewmodel.model.ListCharacterState


fun getStateListCharacterScreen(
    isError: Boolean = false,
    isGenericError: Boolean = false,
    loadingCenter: Boolean = false,
    loadingBottom: Boolean = false
) = ListCharacterState(
    isError = isError,
    isGenericError = isGenericError,
    isLoading = loadingCenter,
    isLoadingPagination = loadingBottom,
    title = "Personagens",
    description = "Listagem de personanges da Marvel",
    listCharacter = mutableListOf(
        CharacterModel(
            name = "Homem de ferro 1",
            id = "1",
            description = "Descrição detalhada 1 sobre cada personagem",
        ),
        CharacterModel(
            name = "Homem de ferro 2",
            id = "2",
            description = "Descrição detalhada 2 sobre cada personagem",
        ),
        CharacterModel(
            name = "Homem de ferro 3",
            id = "3",
            description = "Descrição  detalhada 3 sobre cada personagem",
        ),
        CharacterModel(
            name = "Homem de ferro 4",
            id = "4",
            description = "Descrição detalhada 4 sobre cada personagem",
        ),
    )
)