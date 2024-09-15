package com.project.desafio_jpc.list.presentation.list.viewmodel.model

sealed class CharacterEvent {
    data class NavigateToDetail(val id: String) : CharacterEvent()
    data object NavigateToBack : CharacterEvent()
    data object NavigateToProfile : CharacterEvent()
    data object Nothing : CharacterEvent()
}