package com.fueled.technicalchallenge.domain.repository

import com.fueled.technicalchallenge.domain.model.CharacterDomain
import com.fueled.technicalchallenge.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getCharacters(): Flow<NetworkResult<List<CharacterDomain>>>
}