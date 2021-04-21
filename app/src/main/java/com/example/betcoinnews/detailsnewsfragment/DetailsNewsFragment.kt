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
import com.example.betcoinnews.response.Article
import com.example.betcoinnews.response.BetCoinResponse
import com.example.betcoinnews.util.UtilBuilder
import com.squareup.picasso.Picasso

class DetailsNewsFragment : Fragment() {

    lateinit var binding                : FragmentDetailsNewsBinding
    private val detailsNewsViewModel    : DetailsNewsViewModel by viewModels()
    private lateinit var mBetCoinNews   : Article
    private lateinit var mWallStreet    : Article
    private lateinit var mAppleNews     : Article
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Connect whit view model
        binding.lifecycleOwner      = this
        binding.detailsNewsVarModel = detailsNewsViewModel

        // Check argument come from latest news and show details for it
        if( arguments?.containsKey(UtilBuilder.DETAILS_BET_COIN_NEWS) == true){

            mBetCoinNews = arguments?.getSerializable(UtilBuilder.DETAILS_BET_COIN_NEWS) as Article

            Picasso.get().load(mBetCoinNews.urlToImage).into(binding.ivDetailsNews)
            binding.tvTitleDetailsNews.text = mBetCoinNews.title
            binding.tvDescriptionDetailsNews.text = mBetCoinNews.description

        }

        // Check argument come from other news when click on item WallStreet will show details for it
        if( arguments?.containsKey(UtilBuilder.DETAILS_WILL_STREET_NEWS) == true){

            mWallStreet = arguments?.getSerializable(UtilBuilder.DETAILS_WILL_STREET_NEWS) as Article

            Picasso.get().load(mWallStreet.urlToImage).into(binding.ivDetailsNews)
            binding.tvTitleDetailsNews.text = mWallStreet.title
            binding.tvDescriptionDetailsNews.text = mWallStreet.description
        }

        // Check argument come from other news when click on item AppleNews will show details for it
        if(arguments?.containsKey(UtilBuilder.DETAILS_APPLE_NEWS) == true){

            mAppleNews = arguments?.getSerializable(UtilBuilder.DETAILS_APPLE_NEWS) as Article

            Picasso.get().load(mAppleNews.urlToImage).into(binding.ivDetailsNews)
            binding.tvTitleDetailsNews.text = mAppleNews.title
            binding.tvDescriptionDetailsNews.text = mAppleNews.description
        }
    }
}