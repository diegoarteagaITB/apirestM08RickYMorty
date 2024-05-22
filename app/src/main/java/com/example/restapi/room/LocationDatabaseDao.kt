package com.example.restapi.room

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.restapi.roommodels.CharacterEntity
import com.example.restapi.roommodels.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationDatabaseDao {

    @Query("SELECT * FROM LOCATION")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM LOCATION WHERE id = :locationId")
    fun getLocationById(locationId: Int): LocationEntity


}
