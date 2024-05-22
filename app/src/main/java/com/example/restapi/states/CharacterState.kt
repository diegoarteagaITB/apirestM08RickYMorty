package com.example.restapi.states

import com.example.restapi.roommodels.CharacterEntity

data class CharacterState(
    val characterList: List<CharacterEntity> = emptyList()
) {
}