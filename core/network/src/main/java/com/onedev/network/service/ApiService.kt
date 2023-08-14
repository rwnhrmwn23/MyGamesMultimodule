package com.onedev.network.service

import com.onedev.network.BuildConfig.API_KEY_VALUE
import com.onedev.network.data.response.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(GAMES)
    suspend fun games(
        @Query(KEY) key : String? = API_KEY_VALUE
    ): Response<GamesResponse>


    companion object {
        const val KEY = "key"
        const val GAMES = "games"
    }
}