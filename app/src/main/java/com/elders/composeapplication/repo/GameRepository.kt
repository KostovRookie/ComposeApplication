package com.elders.composeapplication.repo

import com.elders.composeapplication.data.remote.GameService
import com.elders.composeapplication.data.remote.models.SpecificGameModel
import com.elders.composeapplication.domain.items.GameItem
import com.elders.composeapplication.domain.items.SpecificGameItem
import com.elders.composeapplication.domain.items.toGameItem
import com.elders.composeapplication.domain.items.toSpecificGameItem
import javax.inject.Inject

class GameRepository @Inject constructor(private val gameService: GameService) {

    suspend fun getGames() : List<GameItem> {
        return gameService.getGames().map{
            it.toGameItem()
        }
    }

    suspend fun getGameById(id:Int) : SpecificGameItem {
        return gameService.getGameById(id).toSpecificGameItem()
    }


}