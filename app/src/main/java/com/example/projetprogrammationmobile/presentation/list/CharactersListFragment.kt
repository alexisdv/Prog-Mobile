package com.example.projetprogrammationmobile.presentation.list;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetprogrammationmobile.R

class CharactersListFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView

    private lateinit var loader: ProgressBar

    private lateinit var textViewError: TextView

    private val adapter = CharactersAdapter(listOf(), ::onClickedCharacter)

    private val viewModel: CharactersListViewModel by viewModels()

    private val viewModel2: CharactersListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.characters_recyclerview)
        loader = view.findViewById(R.id.characters_loader)
        textViewError = view.findViewById(R.id.characters_error)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CharactersListFragment.adapter
        }

        viewModel.callApi()
        viewModel.charactersList.observe(viewLifecycleOwner, Observer { charactersModel ->
            loader.isVisible = charactersModel is CharactersLoader
            textViewError.isVisible = charactersModel is CharactersError

            if(charactersModel is CharactersSuccess) {
                with(adapter) { updateList(list = charactersModel.charactersList) }
            }
        })

        viewModel2.goNextPage()
        viewModel2.charactersList.observe(viewLifecycleOwner, Observer { charactersModel2 ->
            loader.isVisible = charactersModel2 is CharactersLoader
            textViewError.isVisible = charactersModel2 is CharactersError

            if(charactersModel2 is CharactersSuccess) {
                with(adapter) { getListeSecond(list = charactersModel2.charactersList) }
            }
        })
    }

    private fun onClickedCharacter(id: Int) {
        findNavController().navigate(R.id.navigateToCharactersDetailFragment, bundleOf(
            "charactersId" to (id + 1)
        ))
    }
}
