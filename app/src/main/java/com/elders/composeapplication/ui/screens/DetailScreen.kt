package com.elders.composeapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.*
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.elders.composeapplication.domain.GetGameByIdUseCase
import com.elders.composeapplication.domain.items.SpecificGameItem
import com.elders.composeapplication.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getGameByIdUseCase: GetGameByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val gameId = savedStateHandle.get<String>(Constants.KEY_GAME_ID)?.toInt()
        ?: throw NullPointerException("Parameter userId is required")

    private val _game = MutableLiveData<SpecificGameItem>()
    val game: LiveData<SpecificGameItem> get() = _game

    fun getGameById() {
        viewModelScope.launch {
            try {
                val game = getGameByIdUseCase(gameId)
                _game.value = game
            } catch (_: Exception) {
            }
        }
    }


}

@Composable
fun DetailScreen(gameViewModel: DetailsViewModel = hiltViewModel(), navController: NavController) {

    LaunchedEffect(gameViewModel) {
        gameViewModel.getGameById()
    }



    val game = gameViewModel.game.observeAsState().value

    LazyColumn {
        item {
            Column {


                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)

                        }
                    },
                    title = {
                        Text(text = game?.title ?: "", fontWeight = FontWeight.Bold)
                    }
                )
                Image(
                    painter = rememberImagePainter(game?.thumbnail), contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )


                Text(text = game?.description ?: "", modifier = Modifier.padding(10.dp))
            }
        }
    }
}
