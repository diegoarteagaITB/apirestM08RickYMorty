package com.example.restapi.roommodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class InfoEntity(
    @PrimaryKey val id: Int,
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)