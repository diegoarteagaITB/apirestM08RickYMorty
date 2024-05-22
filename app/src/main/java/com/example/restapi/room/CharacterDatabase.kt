package com.example.restapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restapi.roommodels.CharacterEntity


@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDatabaseDao

}