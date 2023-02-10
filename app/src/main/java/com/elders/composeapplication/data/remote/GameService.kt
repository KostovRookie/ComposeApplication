package com.elders.composeapplication.data.remote

import com.elders.composeapplication.data.remote.models.GameModel
import com.elders.composeapplication.data.remote.models.SpecificGameModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameService @Inject constructor(private val gameApi: GameApi) {

    suspend fun getGames() : List<GameModel> {
        return withContext(Dispatchers.IO){
            val games = gameApi.getGames()
            games.body()?: emptyList()
        }
    }
    suspend fun getGameById(id:Int) : SpecificGameModel {
        return withContext(Dispatchers.IO) {
            val game = gameApi.getGameById(id)
            game.body()!!
        }
    }
}