package com.project.desafio_jpc.list.presentation.list.viewmodel.model

import com.project.desafio_jpc.list.domain.model.CharacterModel

data class ListCharacterState(
    val title: String = "Listagem de personagens",
    val description: String = "Aqui você encontra todos os personagens da Marvel",
    val isLoading: Boolean = false,
    val isLoadingPagination: Boolean = false,
    val tabSelected: Int = 0,
    var listCharacter: MutableList<CharacterModel> = mutableListOf(),
    var page: Int = 0,
    var finishList: Boolean = false,
    val emptyState: Boolean = false,
    val isGenericError: Boolean = false,
    val isError: Boolean = false
) {

    fun getNextPage(incrementPage: Boolean): Int {
        return if (incrementPage) ++page else page
    }
}
