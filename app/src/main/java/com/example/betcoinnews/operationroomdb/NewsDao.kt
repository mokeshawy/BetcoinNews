package com.example.betcoinnews.operationroomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.betcoinnews.model.NewsModel

@Dao
interface NewsDao {

    @Insert
    suspend fun insertNews(newsModel: NewsModel)

    @Query("SELECT * FROM NewsModel WHERE title = :title")
    suspend fun selectByTitle(title : String) : List<NewsModel>

    @Query("SELECT * FROM NewsModel")
    suspend fun selectAllNews() : List<NewsModel>

    @Query("DELETE FROM NewsModel WHERE title = :title")
    suspend fun deleteItems( title: String )
}