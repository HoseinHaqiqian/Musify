package mx.yts.musiclist.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.yts.common.Result
import mx.yts.domain.model.MusicModel
import mx.yts.domain.usecases.GetMusicListUseCase
import javax.inject.Inject

@HiltViewModel
class MusicListViewModel @Inject constructor(
    private var getMusicListUseCase: GetMusicListUseCase
) :
    ViewModel() {
    var counter = 0
    private val musicsState =
        MutableStateFlow<Result<List<MusicModel>>>(Result.Success(emptyList()))
    val musicUiState: StateFlow<Result<List<MusicModel>>> = musicsState

    fun getMusics() {
        viewModelScope.launch {
            musicsState.value = Result.Loading
            getMusicListUseCase.invoke().let { musics ->
                if (musics.isEmpty())
                    musicsState.value = Result.Failure("No data")
                else
                    musicsState.value = Result.Success(musics)

                counter++
            }
        }
    }

}