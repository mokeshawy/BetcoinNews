package com.example.betcoinnews.latestnewsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.betcoinnews.R
import com.example.betcoinnews.adapter.RecyclerLatestNewsAdapter
import com.example.betcoinnews.databinding.FragmentLatestNewsBinding
import com.example.betcoinnews.response.Article

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


        // Call fun for operation api get news betCoin
        latestNewsViewModel.getBetCoinNews( binding.rvLatestNews , binding.tvLatestNewsNotFound)
        latestNewsViewModel.latestNewsResponse.observe(viewLifecycleOwner , Observer {
            binding.rvLatestNews.adapter = RecyclerLatestNewsAdapter(it.articles , this)
        })
    }

    override fun onClickItem(viewHolder: RecyclerLatestNewsAdapter.ViewHolder, dataSet: Article, position: Int) {
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(requireActivity() , dataSet.title , Toast.LENGTH_SHORT).show()
        }
    }

}