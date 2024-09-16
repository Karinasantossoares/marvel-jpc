package com.project.desafio_jpc.list.presentation.detail.viewmodel.model

import com.project.desafio_jpc.list.domain.model.CharacterDetailModel


data class CharacterDetailState(
    val isLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val urlImage: String = "",
    val listComics: List<String> = emptyList(),
    val isGenericError: Boolean = false,
    val isError: Boolean = false,
    val textButton: String = ""
) {

    fun setLoading(value: Boolean) = copy(isLoading = value)
    fun setSuccess(detailModel: CharacterDetailModel) = copy(
        title = detailModel.name.orEmpty(),
        urlImage = detailModel.thumbnail?.getUrlImage().orEmpty(),
        description = detailModel.description.orEmpty()
            .ifEmpty { "Sem descrição" },
        listComics = detailModel.comics?.itemsModel?.map {
            it.name.orEmpty()
        }.orEmpty()
    )


}