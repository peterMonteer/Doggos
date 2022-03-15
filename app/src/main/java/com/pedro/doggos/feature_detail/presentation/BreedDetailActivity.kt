package com.pedro.doggos.feature_detail.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.pedro.doggos.R
import com.pedro.doggos.databinding.ActivityBreedDetailBinding
import com.pedro.doggos.databinding.ActivityMainBinding
import com.pedro.doggos.databinding.FragmentSearchBinding

const val NAME = "NAME"
const val GROUP = "GROUP"
const val ORIGIN = "ORIGIN"
const val TEMPERAMENT = "TEMPERAMENT"

class BreedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBreedDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBreedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameTextView = binding.nameTextView
        val groupTextView = binding.groupTextView
        val originTextView = binding.originTextView
        val temperamentTextView = binding.temperamentTextView

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            nameTextView.text = bundle.getString(NAME)
            groupTextView.text = bundle.getString(GROUP)
            originTextView.text = bundle.getString(ORIGIN)
            temperamentTextView.text = bundle.getString(TEMPERAMENT)
        }
    }
}