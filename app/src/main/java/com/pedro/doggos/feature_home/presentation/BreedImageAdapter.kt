package com.pedro.doggos.feature_home.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pedro.doggos.R
import com.pedro.doggos.feature_home.domain.model.BreedImage
import com.squareup.picasso.Picasso

class BreedImageAdapter(
    private val breedImageList: List<BreedImage>
): RecyclerView.Adapter<BreedImageAdapter.ViewHolder>() {

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val breedImage: ImageView = view.findViewById(R.id.breed_image)
        val breedName: TextView = view.findViewById(R.id.breed_text)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BreedImageAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.breed_image_item,
            viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedImageAdapter.ViewHolder, position: Int) {
        if (breedImageList[position].breeds.isNotEmpty()){
            holder.breedName.text = breedImageList[position].breeds[0].name
        }
        Picasso.get().load(breedImageList[position].url).into(holder.breedImage)
    }

    override fun getItemCount() = breedImageList.size
}