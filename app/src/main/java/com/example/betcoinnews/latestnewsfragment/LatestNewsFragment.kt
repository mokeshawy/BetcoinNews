package com.example.betcoinnews.latestnewsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.betcoinnews.R
import com.example.betcoinnews.adapter.RecyclerLatestNewsAdapter
import com.example.betcoinnews.databinding.FragmentLatestNewsBinding
import com.example.betcoinnews.response.Article
import com.example.betcoinnews.util.UtilBuilder
import com.example.betcoinnews.viewpagerfragment.ViewPagerFragmentDirections

class LatestNewsFragment : Fragment() , RecyclerLatestNewsAdapter.OnClick{

    lateinit var binding            : FragmentLatestNewsBinding
    private val latestNewsViewModel : LatestNewsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLatestNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Connect whit viewModel
        binding.lifecycleOwner      = this
        binding.latestNewsVarModel  = latestNewsViewModel


        // Show progress dialog
        UtilBuilder.showProgressDialog(getString(R.string.tv_progress_bar_loading) , requireActivity())

        // Call fun for operation api get news betCoin
        latestNewsViewModel.getBetCoinNews( binding.rvLatestNews , binding.tvLatestNewsNotFound)
        latestNewsViewModel.latestNewsResponse.observe(viewLifecycleOwner , Observer {
            binding.rvLatestNews.adapter = RecyclerLatestNewsAdapter(it.articles , this )
            // Hide progress
            UtilBuilder.hideProgressDialog()
        })

    }

    // Override fun onClick from interface
    override fun onClickItem(viewHolder: RecyclerLatestNewsAdapter.ViewHolder, dataSet: Article, position: Int) {

        // Make check on select item continuous
        latestNewsViewModel.checkForToggleButton( requireActivity() , dataSet.title , viewHolder.binding.togButtSave)

        // Go to details after click on item
        viewHolder.itemView.setOnClickListener {
             var bundle = Bundle()
            bundle.putSerializable(UtilBuilder.DETAILS_BET_COIN_NEWS,dataSet)
            findNavController().navigate(R.id.action_viewPagerFragment_to_detailsNewsFragment , bundle)
        }
    }

}