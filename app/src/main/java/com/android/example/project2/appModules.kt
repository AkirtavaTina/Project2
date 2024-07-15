package com.android.example.project2

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val myModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(MyApi::class.java) }
}

val repositoryModule = module {
    single { MoviesRepository(get()) }
}

val viewModelModule = module {
    viewModelOf(::MoviesViewModel)
}

val appModule = listOf(myModule, repositoryModule, viewModelModule)
