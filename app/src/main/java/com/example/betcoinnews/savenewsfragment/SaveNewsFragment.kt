package com.example.betcoinnews.savenewsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.betcoinnews.R
import com.example.betcoinnews.adapter.RecyclerSaveNewsAdapter
import com.example.betcoinnews.databinding.FragmentSaveNewsBinding
import com.example.betcoinnews.model.NewsModel
import com.example.betcoinnews.response.Article

class SaveNewsFragment : Fragment() , RecyclerSaveNewsAdapter.OnClick{

    lateinit var binding: FragmentSaveNewsBinding
    private val saveNewsViewModel : SaveNewsViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSaveNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.saveNewsVarModel = saveNewsViewModel

        // Show data from room data base
        saveNewsViewModel.showSaveNewsFromRoom(requireActivity() , binding.rvFavoriteNews , binding.tvLatestFavoriteNotFound)
        saveNewsViewModel.showSaveNews.observe(viewLifecycleOwner , Observer {
            binding.rvFavoriteNews.adapter = RecyclerSaveNewsAdapter(it , this)
        })

        // Operation for refresh option
        saveNewsViewModel.refreshData(requireActivity() , binding.swipeLayout , binding.rvFavoriteNews , binding.tvLatestFavoriteNotFound)
    }

    override fun onClickItem(viewHolder: RecyclerSaveNewsAdapter.ViewHolder, dataSet: NewsModel, position: Int) {

        // Make check on select item continuous
        saveNewsViewModel.checkForToggleButton( requireActivity() , dataSet.title , viewHolder.binding.togButtSave)

        viewHolder.binding.togButtSave.setOnClickListener {
            saveNewsViewModel.deleteFromRoomDataBase(requireActivity() , dataSet.title)
        }

    }
}