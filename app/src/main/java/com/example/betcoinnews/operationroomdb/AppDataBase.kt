package com.example.betcoinnews.operationroomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.betcoinnews.model.NewsModel

@Database (entities = [NewsModel::class] , version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun newsDao() : NewsDao
}