package com.example.restapi.model


data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    constructor() : this(
        created = "",
        episode = emptyList(),
        gender = "",
        id = 0,
        image = "",
        location = Location("", ""),
        name = "",
        origin = Origin("", ""),
        species = "",
        status = "",
        type = "",
        url = ""
    )
}