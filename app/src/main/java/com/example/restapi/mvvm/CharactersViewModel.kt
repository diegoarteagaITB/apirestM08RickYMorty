package com.example.restapi.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapi.ClientService.RetrofitServiceFactory
import com.example.restapi.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel : ViewModel(
) {



    private val _charactersList = MutableLiveData<List<Character>>()
    val charactersList: LiveData<List<Character>> = _charactersList

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitServiceFactory.makeRetrofitService().getAllCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _charactersList.value = response.body()?.results ?: emptyList()
                } else {
                    Log.e("CharactersViewModel", "Failed to fetch characters: ${response.message()}")
                }
            }
        }
    }

    fun getCharacterById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitServiceFactory.makeRetrofitService().getCharacterById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _character.value = response.body()
                } else {
                    Log.e("CharactersViewModel", "Failed to fetch character: ${response.message()}")
                }
            }
        }
    }


}
