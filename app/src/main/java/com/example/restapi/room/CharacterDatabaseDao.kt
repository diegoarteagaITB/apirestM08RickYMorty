package com.example.restapi.room

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.restapi.roommodels.CharacterEntity
import kotlinx.coroutines.flow.Flow


interface CharacterDatabaseDao {

    @Query("SELECT * FROM CHARACTER")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM CHARACTER WHERE id = :id")
    fun getUserById(id: Int): Flow<CharacterEntity>


    @Query("SELECT * FROM CHARACTER WHERE fav = :fav")
    fun getFavCharacters(fav: Boolean): Flow<List<CharacterEntity>>

    @Insert
    fun addCharacter(character: CharacterEntity)

    @Update()
    suspend fun deleteCharacterFromFavs(character: CharacterEntity)

    @Update
    suspend fun addCharacterToFavs(character: CharacterEntity)
}