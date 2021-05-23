package com.example.projetprogrammationmobile.presentation.list

sealed class CharactersModel

data class CharactersSuccess(val charactersList: List<Characters>) : CharactersModel()
object CharactersLoader : CharactersModel()
object CharactersError : CharactersModel()