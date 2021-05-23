package com.example.projetprogrammationmobile.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersAPI{
    @GET("character")
    fun getCharactersList(): Call<CharactersListResponse>

    @GET("character/{id}")
    fun getCharactersDetail(@Path("id") id: Int): Call<CharactersDetailResponse>

    @GET("character/{page}")
    fun getNextPage(@Path("nextPage") nextPage: String): Call<CharactersListResponse>
}