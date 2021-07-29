package com.example.desafio_tecnico.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContatoViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Contato>>
    private val repository: ContatoRepository

    init {
        val contatoDAO = ContatoDatabase.getDatabase(application).contatoDao()
        repository = ContatoRepository(contatoDAO)
        readAllData = repository.readAllData
    }

    fun addContact(contato: Contato){
        viewModelScope.launch(Dispatchers.IO){
            repository.addContact(contato)
        }
    }

}