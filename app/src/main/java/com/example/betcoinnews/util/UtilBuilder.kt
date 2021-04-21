package com.example.betcoinnews.util

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import androidx.room.Room
import com.example.betcoinnews.R
import com.example.betcoinnews.operationroomdb.AppDataBase

object UtilBuilder {

    const val DATA_BASE_NAME            : String = "NewsDataBase"
    const val DETAILS_BET_COIN_NEWS     : String = "betCoin"
    const val DETAILS_WILL_STREET_NEWS  : String = "wallStreetJournal"
    const val DETAILS_APPLE_NEWS        : String = "appleNews"

    lateinit var mProgressDialog : Dialog
    fun showProgressDialog( text : String , context: Context){

        mProgressDialog = Dialog(context)

        mProgressDialog.setContentView(R.layout.custom_dialg)

        var dialog = mProgressDialog.findViewById(R.id.tv_progress_dialog) as TextView
        dialog.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        mProgressDialog.show()
    }

    // hide progress bar
    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }
}