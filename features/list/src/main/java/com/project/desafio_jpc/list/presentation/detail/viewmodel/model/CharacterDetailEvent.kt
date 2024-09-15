package com.project.desafio_jpc.list.presentation.detail.viewmodel.model

sealed class CharacterDetailEvent() {
    data object Nothing : CharacterDetailEvent()
    data object OnBack : CharacterDetailEvent()
}