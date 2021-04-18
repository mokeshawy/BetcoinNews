package com.example.betcoinnews.savenewsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.betcoinnews.R
import com.example.betcoinnews.databinding.FragmentSaveNewsBinding

class SaveNewsFragment : Fragment() {

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
    }
}