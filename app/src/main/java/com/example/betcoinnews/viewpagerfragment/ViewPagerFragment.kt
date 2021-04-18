package com.example.betcoinnews.viewpagerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.betcoinnews.R
import com.example.betcoinnews.databinding.FragmentViewPagerBinding
import com.example.betcoinnews.latestnewsfragment.LatestNewsFragment
import com.example.betcoinnews.othernewsfragment.OtherNewsFragment
import com.example.betcoinnews.savenewsfragment.SaveNewsFragment
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment() {

    lateinit var binding    : FragmentViewPagerBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout   = binding.tabLayout
        val viewPager2  = binding.viewPager2

        var fragmentList = arrayListOf<Fragment>(
                LatestNewsFragment(),
                SaveNewsFragment(),
                OtherNewsFragment()
        )
        var adapter = ViewPagerAdapter(fragmentList ,requireActivity().supportFragmentManager , lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout , viewPager2){tab,position ->
            when(position){
                0 ->{
                    tab.text = "Latest News"
                }
                1 ->{
                    tab.text = "Save News"
                }

                2 ->{
                    tab.text = "Other News"
                }
            }
        }.attach()


    }
}