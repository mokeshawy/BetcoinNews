package com.example.betcoinnews.othernewsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.betcoinnews.R
import com.example.betcoinnews.adapter.RecyclerAppleNewsAdapter
import com.example.betcoinnews.adapter.RecyclerWillStreetNewsAdapter
import com.example.betcoinnews.databinding.FragmentOtherNewsBinding
import com.example.betcoinnews.response.Article
import com.example.betcoinnews.util.UtilBuilder

class OtherNewsFragment : Fragment() , RecyclerAppleNewsAdapter.OnClickAppleNews , RecyclerWillStreetNewsAdapter.OnClickWillStreet{

    lateinit var binding            : FragmentOtherNewsBinding
    private val otherNewsViewModel  : OtherNewsViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtherNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner      = this
        binding.otherNewsVarModel   = otherNewsViewModel

        UtilBuilder.showProgressDialog(resources.getString(R.string.tv_progress_bar_loading) , requireActivity())
        otherNewsViewModel.wallStreetJournal()
        otherNewsViewModel.wallStreetJournal.observe(viewLifecycleOwner, Observer {
            binding.rvOtherNews.adapter = RecyclerWillStreetNewsAdapter(it.articles , this)
            UtilBuilder.hideProgressDialog()
        })

        otherNewsViewModel.appleNews()
        otherNewsViewModel.appleNews.observe(viewLifecycleOwner, Observer {
            binding.rvOtherNewsExtra.adapter = RecyclerAppleNewsAdapter(it.articles , this)
            UtilBuilder.hideProgressDialog()
        })
    }

    override fun onClickAppleNews( viewHolder: RecyclerAppleNewsAdapter.ViewHolder,dataSet: Article, position: Int) {

        viewHolder.itemView.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable(UtilBuilder.DETAILS_APPLE_NEWS,dataSet)
            findNavController().navigate(R.id.action_viewPagerFragment_to_detailsNewsFragment , bundle)
        }
    }

    override fun onClickWillStreet(viewHolder: RecyclerWillStreetNewsAdapter.ViewHolder, dataSet: Article, position: Int) {
        viewHolder.binding.ivItemLatestNews.setOnClickListener {
            var bundle = Bundle()
            bundle.putSerializable(UtilBuilder.DETAILS_WILL_STREET_NEWS,dataSet)
            findNavController().navigate(R.id.action_viewPagerFragment_to_detailsNewsFragment , bundle)
        }
    }


}