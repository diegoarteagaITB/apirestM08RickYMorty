package com.example.restapi.room

import androidx.room.Query
import com.example.restapi.roommodels.CharacterEntity
import com.example.restapi.roommodels.LocationEntity
import kotlinx.coroutines.flow.Flow

interface OriginDatabaseDao {

    @Query("SELECT * FROM ORIGIN")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM Origin WHERE id = :originId")
    fun getOriginById(originId: Int): LocationEntity


}
