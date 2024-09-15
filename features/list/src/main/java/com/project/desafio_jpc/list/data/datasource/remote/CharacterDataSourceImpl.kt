package com.project.desafio_jpc.list.data.datasource.remote

import com.project.desafio_jpc.commom.ConnectionException
import com.project.desafio_jpc.commom.HttpException
import com.project.desafio_jpc.list.data.mapper.toModel
import com.project.desafio_jpc.list.data.service.CharacterService
import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.model.CharacterModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.IOException

internal class CharacterRemoteRemoteDataSourceImpl(
    private val service: CharacterService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharacterRemoteDataSource {

    override fun getAllCharacters(incrementPage: String?): Flow<List<CharacterModel>> {
        return flow { emit(service.getAllCharacters(incrementPage)) }.flowOn(dispatcher).map {
            it.data.results?.toModel().orEmpty()
        }.catch { exception ->
            throw when (exception) {
                is IOException -> ConnectionException
                else -> HttpException
            }
        }
    }

    override fun getDetailCharacters(id: String): Flow<CharacterDetailModel> {
        return flow { emit(service.getDetailCharacter(id)) }.flowOn(dispatcher).map {
            it.toModel()
        }.catch { exception ->
            throw when (exception) {
                is IOException -> ConnectionException
                else -> HttpException
            }
        }
    }
}