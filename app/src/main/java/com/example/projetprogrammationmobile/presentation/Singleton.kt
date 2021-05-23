package com.example.projetprogrammationmobile.presentation

import com.example.projetprogrammationmobile.presentation.api.CharactersAPI
import com.example.projetprogrammationmobile.presentation.CharactersApplication.Companion.context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singleton {
    companion object {
        var cache: Cache = Cache(File(context?.cacheDir, "response"), 10 * 1024 * 1024)

        val OkHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        val charactersApi: CharactersAPI = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient)
            .build()
            .create(CharactersAPI::class.java)
    }
}