package com.example.projetprogrammationmobile.presentation.api
import com.example.projetprogrammationmobile.presentation.list.Characters

data class CharactersListResponse(
    val prev: String,
    val next: String,
    val results: List<Characters>
    )