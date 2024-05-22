package com.example.restapi.roommodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "origin")
data class OriginEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String
)