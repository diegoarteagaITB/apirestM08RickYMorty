package com.example.restapi.room

import androidx.room.Insert
import androidx.room.Query
import com.example.restapi.roommodels.CharacterEntity
import com.example.restapi.roommodels.InfoEntity
import kotlinx.coroutines.flow.Flow

interface InfoDatabaseDao {


    @Query("SELECT * FROM INFO")
    fun getCharacters(): Flow<List<CharacterEntity>>


    @Insert
    fun addInfo(info: InfoEntity)
}