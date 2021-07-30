package com.example.desafio_tecnico.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.desafio_tecnico.database.ContatoDatabase
import com.example.desafio_tecnico.repository.ContatoRepository
import com.example.desafio_tecnico.model.Contato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContatoViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Contato>>
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

    fun updateContact(contato: Contato){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateContact(contato)
        }
    }

    fun deleteContact(contato: Contato){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteContact(contato)
        }
    }
}