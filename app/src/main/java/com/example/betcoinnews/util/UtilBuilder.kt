package com.example.betcoinnews.util

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import androidx.room.Room
import com.example.betcoinnews.R
import com.example.betcoinnews.operationroomdb.AppDataBase

object UtilBuilder {

     const val DATA_BASE_NAME : String = "NewsDataBase"

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