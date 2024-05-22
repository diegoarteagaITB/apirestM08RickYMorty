package com.example.restapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restapi.roommodels.InfoEntity

@Database(
    entities = [InfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class InfoDatabase: RoomDatabase() {
    abstract fun infoDao(): InfoDatabaseDao

}