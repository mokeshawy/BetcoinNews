package com.example.betcoinnews.othernewsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.betcoinnews.operationretrofet.ServiceBuilder
import com.example.betcoinnews.response.BetCoinResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class OtherNewsViewModel : ViewModel() {

    var wallStreetJournal   = MutableLiveData<BetCoinResponse>()
    var appleNews           = MutableLiveData<BetCoinResponse>()

    fun wallStreetJournal(){

        CoroutineScope(Dispatchers.IO).async {

           var response = ServiceBuilder.makeRetrofit().getWallStreetJournal("wsj.com" , "9b3d814ad7e840fa97fa9608886787f5")

            CoroutineScope(Dispatchers.Main).async {

                wallStreetJournal.value = response.body()
            }
        }
    }


    fun appleNews(){

        CoroutineScope(Dispatchers.IO).async {

            var response = ServiceBuilder.makeRetrofit().getAppleNews("apple" , "2021-04-20" , "2021-04-20" , "popularity" , "9b3d814ad7e840fa97fa9608886787f5")

            CoroutineScope(Dispatchers.Main).async {

                appleNews.value = response.body()
            }
        }
    }
}