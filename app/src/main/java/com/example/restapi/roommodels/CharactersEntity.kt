package com.example.restapi.roommodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey val id: Int,
    val infoId: Int, // Identificador para la información de los personajes
    val results: List<CharacterEntity>
)