package com.example.betcoinnews.othernewsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.betcoinnews.R
import com.example.betcoinnews.databinding.FragmentOtherNewsBinding

class OtherNewsFragment : Fragment() {

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
    }
}