package com.example.projetprogrammationmobile.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetprogrammationmobile.R

class CharactersAdapter(private var dataSet: List<Characters>, val listener: ((Int) -> Unit)? = null) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>()  {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    private var listeSecond : List<Characters>? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewG: TextView
        val imageViewG: ImageView
        val textViewD: TextView
        val imageViewD: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textViewG = view.findViewById(R.id.characters_name_gauche)
            imageViewG = view.findViewById(R.id.characters_img_gauche)
            textViewD = view.findViewById(R.id.characters_name_droite)
            imageViewD = view.findViewById(R.id.characters_img_droite)
        }
    }

    fun updateList(list: List<Characters>){
        dataSet = list
        notifyDataSetChanged()
    }

    fun getListeSecond(list: List<Characters>)
    {
        listeSecond = list
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_listeprojet, viewGroup, false)

        return ViewHolder(view)
    }

    private fun divideList(list: List<Characters>, num : Int): MutableList<Characters> {
        val listeG : MutableList<Characters> = mutableListOf()
        val listeD : MutableList<Characters> = mutableListOf()
        var compteur = 0
        for(item in list)
        {
            if(compteur == 0 || compteur%2 == 0)
            {
                listeG.add(item)
            }
            else
            {
                listeD.add(item)
            }
            compteur += 1
        }
        return if(num == 1) {
            listeG
        } else {
            listeD
        }
    }

    private fun divideSecondList(list: List<Characters>, listG : MutableList<Characters>, listD : MutableList<Characters>, num : Int): MutableList<Characters> {
        var compteur = 0
        for(item in list)
        {
            if(compteur == 0 || compteur%2 == 0)
            {
                listG.add(item)
            }
            else
            {
                listD.add(item)
            }
            compteur += 1
        }
        return if(num == 1) {
            listG
        } else {
            listD
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if(position < (itemCount/2))
        {
            val listeG = divideList(dataSet, 1)
            val listeD = divideList(dataSet, 2)

            val charactersG = listeG[position]
            viewHolder.textViewG.text = charactersG.name
            val posG = (position*2)
            Glide
                .with(viewHolder.itemView.context)
                .load("https://rickandmortyapi.com/api/character/avatar/${posG + 1}.jpeg")
                .centerCrop()
                .into(viewHolder.imageViewG);


            viewHolder.imageViewG.setOnClickListener {
                listener?.invoke(posG)
            }

            val charactersD = listeD[position]
            viewHolder.textViewD.text = charactersD.name
            val posD = posG + 1
            Glide
                .with(viewHolder.itemView.context)
                .load("https://rickandmortyapi.com/api/character/avatar/${posD + 1}.jpeg")
                .centerCrop()
                .into(viewHolder.imageViewD);


            viewHolder.imageViewD.setOnClickListener {
                listener?.invoke(posD)
            }
        }
        else if(position >= (itemCount/2))
        {
            val listeGalmost = divideList(dataSet, 1)
            val listeDalmost = divideList(dataSet, 2)

            val listeG = listeSecond?.let { divideSecondList(it, listeGalmost, listeDalmost, 1) }
            val listeD = listeSecond?.let { divideSecondList(it, listeGalmost, listeDalmost, 2) }

            val charactersG = listeG?.get(position)
            if (charactersG != null) {
                viewHolder.textViewG.text = charactersG.name
            }
            val posG = (position*2)
            if (charactersG != null) {
                Glide
                    .with(viewHolder.itemView.context)
                    .load("https://rickandmortyapi.com/api/character/avatar/${posG + 1}.jpeg")
                    .centerCrop()
                    .into(viewHolder.imageViewG)
            };


            viewHolder.imageViewG.setOnClickListener {
                listener?.invoke(posG)
            }

            val charactersD = listeD?.get(position)
            if (charactersD != null) {
                viewHolder.textViewD.text = charactersD.name
            }
            val posD = posG + 1
            if (charactersD != null) {
                Glide
                    .with(viewHolder.itemView.context)
                    .load("https://rickandmortyapi.com/api/character/avatar/${posD + 1}.jpeg")
                    .centerCrop()
                    .into(viewHolder.imageViewD)
            };


            viewHolder.imageViewD.setOnClickListener {
                listener?.invoke(posD)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}