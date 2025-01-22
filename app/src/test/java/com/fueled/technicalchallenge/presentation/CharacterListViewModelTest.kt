package com.fueled.technicalchallenge.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.fueled.technicalchallenge.domain.repository.CharactersRepository
import com.fueled.technicalchallenge.presentation.viewmodel.CharacterListViewModel
import com.fueled.technicalchallenge.utils.NetworkResult
import com.fueled.technicalchallenge.utils.TestData
import com.fueled.technicalchallenge.utils.TestData.characterDomain
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val charactersRepository: CharactersRepository = mockk()
    private lateinit var characterListViewModel: CharacterListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        coEvery {
            charactersRepository.getCharacters()
        } returns flowOf(NetworkResult.Success(listOf(characterDomain)))

        Dispatchers.setMain(testDispatcher)

        characterListViewModel = CharacterListViewModel(charactersRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCharacters() update state with list data after success response`() = runTest {
        characterListViewModel.getCharacters()

        characterListViewModel.characterListState.test {
            assertNotNull(awaitItem().characters)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getCharacters() update state with null error message after client error response`() =
        runTest {
            characterListViewModel.getCharacters()

            characterListViewModel.characterListState.test {
                assertNull(awaitItem().error)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `getCharacters() update state with error message after server error response`() = runTest {
        coEvery {
            charactersRepository.getCharacters()
        } returns flowOf(NetworkResult.ServerError(TestData.error))

        characterListViewModel.getCharacters()

        characterListViewModel.characterListState.test {
            assertEquals("Oops! Our Server is Down.", awaitItem().error)
            cancelAndIgnoreRemainingEvents()
        }
    }
}