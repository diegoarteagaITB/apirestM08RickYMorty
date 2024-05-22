package com.example.restapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restapi.roommodels.CharacterEntity
import com.example.restapi.roommodels.LocationEntity


@Database(
    entities = [LocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocationDatabase: RoomDatabase() {
    abstract fun locationDao(): LocationDatabaseDao

}