package com.example.desafio_tecnico.repository

import androidx.lifecycle.LiveData
import com.example.desafio_tecnico.database.ContatoDAO
import com.example.desafio_tecnico.model.Contato

class ContatoRepository(private val contatoDAO: ContatoDAO){

    val readAllData: LiveData<List<Contato>> = contatoDAO.loadContact()

    suspend fun addContact(contato: Contato){
        contatoDAO.saveContact(contato)
    }

    suspend fun updateContact(contato: Contato){
        contatoDAO.updateContact(contato)
    }

    suspend fun deleteContact(contato: Contato){
        contatoDAO.deleteContact(contato)
    }

}