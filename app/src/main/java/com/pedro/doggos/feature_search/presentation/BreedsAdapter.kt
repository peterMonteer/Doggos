package com.pedro.doggos.feature_search.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pedro.doggos.R
import com.pedro.doggos.core.domain.model.Breed

class BreedsAdapter(private val onClick: (Breed) -> Unit): RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    private var breedList: MutableList<Breed> = mutableListOf()

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_text_view)
        val group: TextView = view.findViewById(R.id.group_text_view)
        val origin: TextView = view.findViewById(R.id.origin_text_view)

        init {
            view.setOnClickListener {
                onClick(breedList[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BreedsAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.breed_item,
            viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedsAdapter.ViewHolder, position: Int) {
        if (breedList.isNotEmpty()) {
            val origin = breedList[position].origin
            val group =  breedList[position].group
            holder.name.text = breedList[position].name
            holder.group.text = if (group.isNullOrEmpty()) "Not available" else group
            holder.origin.text = if (origin.isNullOrEmpty()) "Not available" else origin
        }
    }

    override fun getItemCount() = breedList.size

    fun setData(list: List<Breed>) {
        breedList.clear()
        for (breed in list) {
            breedList.add(breed)
        }
        notifyDataSetChanged()
    }
}