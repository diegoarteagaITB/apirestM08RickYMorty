package com.example.restapi.ClientService

import com.example.restapi.model.Character
import com.example.restapi.model.Characters
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitService {

    @GET("api/character")
    suspend fun getAllCharacters(): Response<Characters> // Cambio de tipo de retorno

    @GET("api/character/{id}")
    suspend fun getCharacterById(
        @Path("id") idCharacter: String
    ): Response<Character>
}

object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}