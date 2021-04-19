package com.example.betcoinnews.latestnewsfragment

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Insert
import androidx.room.Room
import com.example.betcoinnews.model.NewsModel
import com.example.betcoinnews.operationretrofet.ServiceBuilder
import com.example.betcoinnews.operationroomdb.AppDataBase
import com.example.betcoinnews.response.BetCoinResponse
import com.example.betcoinnews.util.UtilBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LatestNewsViewModel : ViewModel() {


    var latestNewsResponse = MutableLiveData<BetCoinResponse>()

    // Get news from api
    fun getBetCoinNews( rv_latest_news : RecyclerView , tv_latest_news_not_found : TextView , context: Context){
        CoroutineScope(Dispatchers.IO).async {

            var response = ServiceBuilder.makeRetrofit().getBetCoinNews("bitcoin" , "9b3d814ad7e840fa97fa9608886787f5")

            CoroutineScope(Dispatchers.Main).async {

                latestNewsResponse.value = response.body()

                if(latestNewsResponse.value!!.articles.isNotEmpty()){

                    rv_latest_news.visibility           = View.VISIBLE
                    tv_latest_news_not_found.visibility = View.GONE
                }else{
                    rv_latest_news.visibility           = View.GONE
                    tv_latest_news_not_found.visibility = View.VISIBLE
                }
            }
        }
    }

    // Insert select news to room db
    fun insertNewsToRomDb( context: Context , title : String , description : String , image : String ){
        CoroutineScope(Dispatchers.IO).async {

            var dataBase : AppDataBase = Room.databaseBuilder( context , AppDataBase::class.java , UtilBuilder.DATA_BASE_NAME).build()
            var insertFavNews = NewsModel( title , description , image)

            CoroutineScope(Dispatchers.Main).async {
               var title = dataBase.newsDao().selectByTitle(title)

                if( title.size == 1 ){

                }else{
                    dataBase.newsDao().insertNews(insertFavNews)
                }
            }
        }
    }

    // Make check on select item continuous
    fun checkForToggleButton(context: Context , title: String  , button: ToggleButton){

        CoroutineScope(Dispatchers.IO).async {
            var dataBase : AppDataBase = Room.databaseBuilder(context , AppDataBase::class.java , UtilBuilder.DATA_BASE_NAME).build()

            CoroutineScope(Dispatchers.Main).async {
                var data = dataBase.newsDao().selectAllNews()

                for( itemSelect in data){

                    if(itemSelect.title == title){

                        button.isChecked = true
                    }
                }
            }
        }
    }
}