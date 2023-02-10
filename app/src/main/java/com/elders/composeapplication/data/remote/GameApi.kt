package com.elders.composeapplication.data.remote

import com.elders.composeapplication.data.remote.models.GameModel
import com.elders.composeapplication.data.remote.models.SpecificGameModel
import com.elders.composeapplication.utils.Constants.Companion.GAMES_ENDPOINT
import com.elders.composeapplication.utils.Constants.Companion.GAME_ID_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {

    @GET(GAMES_ENDPOINT)
    suspend fun getGames(): Response<List<GameModel>>

    @GET(GAME_ID_ENDPOINT)
    suspend fun getGameById(
        @Query(value = "id") id: Int
    ): Response<SpecificGameModel>


}