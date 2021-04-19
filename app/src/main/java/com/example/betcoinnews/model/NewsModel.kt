package com.example.betcoinnews.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsModel (

        @ColumnInfo(name = "title")
        var title : String,

        @ColumnInfo(name = "description")
        var description : String,

        @ColumnInfo(name = "urlToImage")
        var urlToImage : String
){

    @PrimaryKey(autoGenerate = true)
    var id = 0
}


