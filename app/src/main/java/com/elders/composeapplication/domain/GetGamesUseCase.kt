package com.elders.composeapplication.domain

import com.elders.composeapplication.domain.items.GameItem
import com.elders.composeapplication.repo.GameRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): List<GameItem> {
        return gameRepository.getGames().shuffled()
    }
}