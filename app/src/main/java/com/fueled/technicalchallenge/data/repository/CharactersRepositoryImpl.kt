package com.fueled.technicalchallenge.data.repository

import com.fueled.technicalchallenge.data.ApiUtils
import com.fueled.technicalchallenge.data.local.dao.CharacterDao
import com.fueled.technicalchallenge.data.mapper.toCharacterDomain
import com.fueled.technicalchallenge.data.mapper.toCharacterEntity
import com.fueled.technicalchallenge.data.network.service.CharactersApi
import com.fueled.technicalchallenge.domain.model.CharacterDomain
import com.fueled.technicalchallenge.domain.repository.CharactersRepository
import com.fueled.technicalchallenge.utils.DispatcherProvider
import com.fueled.technicalchallenge.utils.NetworkResult
import com.fueled.technicalchallenge.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CharactersRepositoryImpl(
    private val charactersApi: CharactersApi,
    private val dispatcherProvider: DispatcherProvider,
    private val characterDao: CharacterDao
) : CharactersRepository {

    override suspend fun getCharacters(): Flow<NetworkResult<List<CharacterDomain>>> {
        return flow {
            val cachedCharacters = characterDao.getCharacters().map {
                it.toCharacterDomain()
            }

            if (cachedCharacters.isNotEmpty()) {
                emit(NetworkResult.Success(cachedCharacters))
            }

            val result = safeApiCall {
                charactersApi.getCharacters(
                    ts = ApiUtils.currentTimestamp, hash = ApiUtils.hash, heroNameQuery = null
                ).results?.map {
                    it.toCharacterDomain()
                } ?: emptyList()
            }

            if (result is NetworkResult.Success) {
                // Cache the fetched data
                val characterEntities = result.data.map {
                    it.toCharacterEntity()
                }

                characterDao.deleteAllCharacters()
                characterDao.insertAll(characterEntities)
            }

            emit(result)
        }.flowOn(dispatcherProvider.io)
    }
}