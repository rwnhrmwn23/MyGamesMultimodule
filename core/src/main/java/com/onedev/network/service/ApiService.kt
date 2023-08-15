package com.onedev.network.service

import com.onedev.network.BuildConfig.API_KEY_VALUE
import com.onedev.network.response.GamesDetailResponse
import com.onedev.network.response.GamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(GAMES)
    suspend fun games(
        @Query(SEARCH) search : String,
        @Query(KEY) key : String? = API_KEY_VALUE
    ): Response<GamesResponse>

    @GET(GAMES_DETAIL)
    suspend fun gamesDetail(
        @Path(ID) id: Int,
        @Query(KEY) key : String? = API_KEY_VALUE
    ): Response<GamesDetailResponse>


    companion object {
        const val ID = "id"
        const val KEY = "key"
        const val SEARCH = "search"
        const val GAMES = "games"
        const val GAMES_DETAIL = "games/{id}"
    }
}