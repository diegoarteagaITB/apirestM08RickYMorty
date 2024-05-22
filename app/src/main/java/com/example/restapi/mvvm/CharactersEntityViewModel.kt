package com.example.restapi.mvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.restapi.model.Character
import com.example.restapi.room.CharacterDatabaseDao
import com.example.restapi.roommodels.CharacterEntity
import com.example.restapi.states.CharacterState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersEntityViewModel(
    private val dao: CharacterDatabaseDao
) : ViewModel() {
    var state by mutableStateOf(CharacterState())
        private set


    init {
        viewModelScope.launch {
            dao.getCharacters().collectLatest {
                state = state.copy(
                    characterList = it
                )
            }
        }
    }

    fun addCharacter(character: CharacterEntity) = viewModelScope.launch {
        dao.addCharacter(character = character)
    }

    fun addCharacterToFavs(character: CharacterEntity) = viewModelScope.launch {
        dao.addCharacterToFavs(character = character)
    }

    fun deleteCharacterFromFavs(character: CharacterEntity) = viewModelScope.launch {
        dao.deleteCharacterFromFavs(character = character)
    }

}