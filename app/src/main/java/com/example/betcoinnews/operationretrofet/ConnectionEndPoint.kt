package com.example.betcoinnews.operationretrofet

import com.example.betcoinnews.response.BetCoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionEndPoint {

    @GET("v2/everything")
    suspend fun getBetCoinNews( @Query("q") q : String , @Query("apiKey")  apiKey : String ) : Response<BetCoinResponse>
}