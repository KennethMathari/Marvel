package com.fueled.technicalchallenge.di

import com.fueled.technicalchallenge.data.ApiConstants
import com.fueled.technicalchallenge.data.UnWrapperFactory
import com.fueled.technicalchallenge.data.local.MarvelDatabase
import com.fueled.technicalchallenge.data.local.dao.CharacterDao
import com.fueled.technicalchallenge.data.network.service.CharactersApi
import com.fueled.technicalchallenge.data.repository.CharactersRepositoryImpl
import com.fueled.technicalchallenge.domain.repository.CharactersRepository
import com.fueled.technicalchallenge.presentation.viewmodel.CharacterListViewModel
import com.fueled.technicalchallenge.utils.DefaultDispatcherProvider
import com.fueled.technicalchallenge.utils.DispatcherProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single<Retrofit> {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder().baseUrl(ApiConstants.BASE_URL).client(get())
            .addConverterFactory(UnWrapperFactory)
            .addConverterFactory(get<Json>().asConverterFactory(contentType)).build()
    }

    single<CharactersApi> {
        val retrofit = get<Retrofit>()
        retrofit.create()
    }

    single<DispatcherProvider> {
        DefaultDispatcherProvider()
    }

    single<MarvelDatabase> {
        MarvelDatabase.getDatabase(get())
    }

    single<CharacterDao> {
        val marvelDatabase = get<MarvelDatabase>()
        marvelDatabase.characterDao()
    }

    single<CharactersRepository> {
        CharactersRepositoryImpl(
            charactersApi = get(), dispatcherProvider = get(), characterDao = get()
        )
    }

    viewModelOf(::CharacterListViewModel)
}
