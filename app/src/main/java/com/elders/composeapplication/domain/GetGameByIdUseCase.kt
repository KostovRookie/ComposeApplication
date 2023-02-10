package com.elders.composeapplication.domain

import com.elders.composeapplication.domain.items.SpecificGameItem
import com.elders.composeapplication.repo.GameRepository
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(private val gameRepository: GameRepository){


    suspend operator fun invoke(id: Int) : SpecificGameItem{
        return gameRepository.getGameById(id)
    }
}