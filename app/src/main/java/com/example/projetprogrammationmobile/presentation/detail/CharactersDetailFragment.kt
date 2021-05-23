package com.example.projetprogrammationmobile.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.projetprogrammationmobile.R
import com.example.projetprogrammationmobile.presentation.Singleton
import com.example.projetprogrammationmobile.presentation.api.CharactersDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersDetailFragment : Fragment() {
    private lateinit var textViewName: TextView
    private lateinit var textViewStatus: TextView
    private lateinit var textViewSpecies: TextView
    private lateinit var textViewGender: TextView
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewName = view.findViewById(R.id.characters_detail_name)
        textViewStatus = view.findViewById(R.id.characters_detail_status)
        textViewSpecies = view.findViewById(R.id.characters_detail_species)
        textViewGender = view.findViewById(R.id.characters_detail_gender)
        image = view.findViewById(R.id.characters_detail_img)
        callApi()
    }

    private fun callApi() {
        val id = arguments?.getInt("charactersId") ?: -1
        Singleton.charactersApi.getCharactersDetail(id).enqueue(object : Callback<CharactersDetailResponse> {

            override fun onFailure(
                call: Call<CharactersDetailResponse>,
                t: Throwable
            ) {

            }
            override fun onResponse(
                call: Call<CharactersDetailResponse>,
                response: Response<CharactersDetailResponse>
            ) {
                if(response.isSuccessful && response.body() != null)
                {
                    textViewName.text = response.body()!!.name
                    textViewStatus.text = response.body()!!.status
                    textViewSpecies.text = response.body()!!.species
                    textViewGender.text = response.body()!!.gender

                    context?.let {
                        Glide
                            .with(it)
                            .load(response.body()!!.image)
                            .into(image)
                    }
                }
            }
        })
    }
}