package com.pedro.doggos.feature_home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pedro.doggos.R
import com.pedro.doggos.core.domain.model.Breed
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.squareup.picasso.Picasso

class BreedImageRxAdapter(private val onClick: (Breed) -> Unit): PagingDataAdapter<BreedImage, BreedImageRxAdapter.ViewHolder>(COMPARATOR) {

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val breedImage: ImageView = view.findViewById(R.id.breed_image)
        val breedName: TextView = view.findViewById(R.id.breed_text)

        init {
            view.setOnClickListener {
                getItem(bindingAdapterPosition)?.breed?.let {
                    onClick(it)
                }
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BreedImageRxAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.breed_image_item,
            viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedImageRxAdapter.ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.breedName.text = it.breed.name
            Picasso.get().load(it.url).into(holder.breedImage)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<BreedImage>() {
            override fun areItemsTheSame(oldItem: BreedImage, newItem: BreedImage): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BreedImage, newItem: BreedImage): Boolean {
                return oldItem == newItem
            }
        }
    }
}