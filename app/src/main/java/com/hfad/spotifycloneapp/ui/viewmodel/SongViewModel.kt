package com.hfad.spotifycloneapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.spotifycloneapp.exoplayer.MusicService
import com.hfad.spotifycloneapp.exoplayer.MusicServiceConnection
import com.hfad.spotifycloneapp.exoplayer.currentPlaybackPosition
import com.hfad.spotifycloneapp.other.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(musicServiceConnection: MusicServiceConnection) :
    ViewModel() {
    private val playBackState = musicServiceConnection.playbackState

    private val _currentSongDuration = MutableLiveData<Long>()
    val curSongDuration: LiveData<Long> = _currentSongDuration

    private val _currPlayerPosition = MutableLiveData<Long>()
    val curPlayerPosition: LiveData<Long> = _currPlayerPosition

    init {
        updateCurrentPlayerPosition()
    }

    private fun updateCurrentPlayerPosition() {
        viewModelScope.launch {
            while (true) {
                val pos = playBackState.value?.currentPlaybackPosition
                if (curPlayerPosition.value != pos) {
                    _currPlayerPosition.postValue(pos!!)
                    _currentSongDuration.postValue(MusicService.curSongDuration)
                }
                delay(Constants.UPDATE_PLAYER_POSITION_INTERVAL)
            }
        }
    }
}