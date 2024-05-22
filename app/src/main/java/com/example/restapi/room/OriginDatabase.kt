package com.example.restapi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restapi.roommodels.OriginEntity


@Database(
    entities = [OriginEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OriginDatabase: RoomDatabase() {
    abstract fun originDao(): OriginDatabaseDao

}