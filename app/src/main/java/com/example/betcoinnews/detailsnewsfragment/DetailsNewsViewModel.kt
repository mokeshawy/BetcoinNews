package com.example.betcoinnews.detailsnewsfragment

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsNewsViewModel : ViewModel() {

    var detailsFragment = MutableLiveData<DetailsNewsFragmentArgs>()

    fun showData( tv_title : TextView){

       tv_title.text =  detailsFragment.value!!.detailsNews.title
    }
}