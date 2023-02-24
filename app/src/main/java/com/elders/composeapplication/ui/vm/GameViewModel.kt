package com.elders.composeapplication.ui.vm

import androidx.lifecycle.*
import com.elders.composeapplication.domain.GetGameByIdUseCase
import com.elders.composeapplication.domain.GetGamesUseCase
import com.elders.composeapplication.domain.items.GameItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _games = MutableLiveData<List<GameItem>>()
    val games: LiveData<List<GameItem>> get() = _games


    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch {
            try {
                val games = getGamesUseCase()
                _games.value = games
            } catch (_: Exception) {
            }
        }
    }

}