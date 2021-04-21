package com.example.betcoinnews.detailsnewsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.betcoinnews.R
import com.example.betcoinnews.databinding.FragmentDetailsNewsBinding
import com.squareup.picasso.Picasso

class DetailsNewsFragment : Fragment() {

    lateinit var binding                : FragmentDetailsNewsBinding
    private val detailsNewsViewModel    : DetailsNewsViewModel by viewModels()
    private val detailsArg              : DetailsNewsFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner      = this
        binding.detailsNewsVarModel = detailsNewsViewModel

        Picasso.get().load(detailsArg.detailsNews.urlToImage).into(binding.ivDetailsNews)
        binding.tvTitleDetailsNews.text = detailsArg.detailsNews.title
        binding.tvDescriptionDetailsNews.text = detailsArg.detailsNews.description
    }
}