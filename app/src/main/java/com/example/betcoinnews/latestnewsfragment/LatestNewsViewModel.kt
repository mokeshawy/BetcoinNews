package com.example.betcoinnews.latestnewsfragment

import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.betcoinnews.operationretrofet.ServiceBuilder
import com.example.betcoinnews.response.BetCoinResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LatestNewsViewModel : ViewModel() {

    var latestNewsResponse = MutableLiveData<BetCoinResponse>()

    fun getBetCoinNews( rv_latest_news : RecyclerView , tv_latest_news_not_found : TextView){

        CoroutineScope(Dispatchers.IO).async {

            var response = ServiceBuilder.makeRetrofit().getBetCoinNews("bitcoin" , "9b3d814ad7e840fa97fa9608886787f5")

            CoroutineScope(Dispatchers.Main).async {

                latestNewsResponse.value = response.body()

                if(latestNewsResponse.value!!.articles.isNotEmpty()){

                    rv_latest_news.visibility = View.VISIBLE
                    tv_latest_news_not_found.visibility = View.GONE
                }else{
                    rv_latest_news.visibility = View.GONE
                    tv_latest_news_not_found.visibility = View.VISIBLE
                }
            }

        }
    }
}