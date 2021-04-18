package com.example.betcoinnews.viewpagerfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.betcoinnews.latestnewsfragment.LatestNewsFragment
import com.example.betcoinnews.othernewsfragment.OtherNewsFragment
import com.example.betcoinnews.savenewsfragment.SaveNewsFragment

class ViewPagerAdapter( items : ArrayList<Fragment> ,  fragmentManager: FragmentManager , lifecycle: Lifecycle) : FragmentStateAdapter( fragmentManager, lifecycle) {
    private val fragmentItems = items
    override fun getItemCount(): Int {

        return fragmentItems.size
    }

    override fun createFragment(position: Int): Fragment {

      return fragmentItems[position]
    }
}