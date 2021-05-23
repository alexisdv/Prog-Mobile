package com.example.projetprogrammationmobile.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetprogrammationmobile.presentation.Singleton
import com.example.projetprogrammationmobile.presentation.api.CharactersListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersListViewModel : ViewModel(){

    val charactersList :MutableLiveData<CharactersModel> = MutableLiveData()

    var nextPage : String? = null

    var prevPage : String? = null

    fun callApi() {
        charactersList.value = CharactersLoader
        Singleton.charactersApi.getCharactersList().enqueue(object : Callback<CharactersListResponse> {
            override fun onFailure(call: Call<CharactersListResponse>, t: Throwable) {
                charactersList.value = CharactersError
            }

            override fun onResponse(call: Call<CharactersListResponse>, response: Response<CharactersListResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val charactersResponse = response.body()!!
                    charactersList.value = CharactersSuccess(charactersResponse.results)
                    nextPage = charactersResponse.next
                    prevPage = charactersResponse.prev
                }
                else {
                    charactersList.value = CharactersError
                }
            }

        })
    }

    fun goNextPage() {
        charactersList.value = CharactersLoader
        nextPage?.let {
            val page = it.substring(42)
            Singleton.charactersApi.getNextPage(page).enqueue(object : Callback<CharactersListResponse> {
                override fun onFailure(call: Call<CharactersListResponse>, t: Throwable) {
                    charactersList.value = CharactersError
                }

                override fun onResponse(call: Call<CharactersListResponse>, response: Response<CharactersListResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val charactersResponse = response.body()!!
                        charactersList.value = CharactersSuccess(charactersResponse.results)
                        nextPage = charactersResponse.next
                        prevPage = charactersResponse.prev
                    } else {
                        charactersList.value = CharactersError
                    }
                }

            })
        }
    }
}
