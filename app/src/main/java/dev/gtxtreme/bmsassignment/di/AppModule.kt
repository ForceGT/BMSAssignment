package dev.gtxtreme.bmsassignment.di

import dev.gtxtreme.bmsassignment.data.repository.MovieDetailRepository
import dev.gtxtreme.bmsassignment.data.repository.MovieDetailRepositoryImpl
import dev.gtxtreme.bmsassignment.data.source.MovieDetailApi
import dev.gtxtreme.bmsassignment.data.source.MovieDetailRemoteSource
import dev.gtxtreme.bmsassignment.data.source.MovieDetailRemoteSourceImpl
import dev.gtxtreme.bmsassignment.domain.usecase.GetMovieDetailUseCase
import dev.gtxtreme.bmsassignment.ui.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Api layer
    single {
        MovieDetailApi(networkManager = get())
    }

    // Remote source
    single<MovieDetailRemoteSource> {
        MovieDetailRemoteSourceImpl(movieApi = get())
    }

    // Repository
    single<MovieDetailRepository>{
        MovieDetailRepositoryImpl(remoteSource = get())
    }


    //Use Case
    single {
        GetMovieDetailUseCase(
            movieDetailRepository = get()
        )
    }

    //View Model
    viewModel {
        MovieDetailViewModel(
            getMovieDetailUseCase = get()
        )
    }
}