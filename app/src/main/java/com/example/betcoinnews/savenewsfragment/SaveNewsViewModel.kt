package com.example.betcoinnews.savenewsfragment

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.betcoinnews.model.NewsModel
import com.example.betcoinnews.operationroomdb.AppDataBase
import com.example.betcoinnews.util.UtilBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.logging.Handler


class SaveNewsViewModel : ViewModel() {

    var showSaveNews = MutableLiveData<List<NewsModel>>()

    // Show data from room data base
    fun showSaveNewsFromRoom(context : Context, rv_latest_news : RecyclerView, tv_latest_news_not_found : TextView){

        CoroutineScope(Dispatchers.IO).async {
            var dataBase : AppDataBase = Room.databaseBuilder( context , AppDataBase::class.java , UtilBuilder.DATA_BASE_NAME).build()

            CoroutineScope(Dispatchers.Main).async {

               showSaveNews.value =  dataBase.newsDao().selectAllNews()

                if(showSaveNews.value!!.isNotEmpty()){
                    rv_latest_news.visibility = View.VISIBLE
                    tv_latest_news_not_found.visibility = View.GONE
                }else{
                    rv_latest_news.visibility = View.GONE
                    tv_latest_news_not_found.visibility = View.VISIBLE
                }
            }
        }
    }

    // Refresh item by swipe refresh layout
    fun refreshData( context : Context , swipeRefreshLayout: SwipeRefreshLayout , rv_latest_news : RecyclerView, tv_latest_news_not_found : TextView){

        swipeRefreshLayout.setOnRefreshListener {
            showSaveNewsFromRoom(context , rv_latest_news , tv_latest_news_not_found)

            @Suppress("DEPRECATION")
            android.os.Handler().postDelayed(Runnable { swipeRefreshLayout.isRefreshing = false } , 2000)
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