package com.fueled.technicalchallenge.data

import app.cash.turbine.test
import com.fueled.technicalchallenge.utils.TestData.characterDomain
import com.fueled.technicalchallenge.utils.TestData.error
import com.fueled.technicalchallenge.domain.repository.CharactersRepository
import com.fueled.technicalchallenge.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CharacterRepositoryTest {

    private val charactersRepository: CharactersRepository = mockk()

    @Test
    fun `getCharacters() Returns Success`() = runTest {
        coEvery {
            charactersRepository.getCharacters()
        } returns flowOf(NetworkResult.Success(listOf(characterDomain)))

        charactersRepository.getCharacters().test {
            assertEquals(NetworkResult.Success(listOf(characterDomain)), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCharacters() Returns ClientError`() = runTest {
        coEvery {
            charactersRepository.getCharacters()
        } returns flowOf(NetworkResult.ClientError(error))

        charactersRepository.getCharacters().test {
            assertEquals(NetworkResult.ClientError(error), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCharacters() Returns ServerError`() = runTest {
        coEvery {
            charactersRepository.getCharacters()
        } returns flowOf(NetworkResult.ServerError(error))

        charactersRepository.getCharacters().test {
            assertEquals(NetworkResult.ServerError(error), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}