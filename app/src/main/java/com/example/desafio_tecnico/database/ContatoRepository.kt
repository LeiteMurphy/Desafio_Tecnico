package com.example.desafio_tecnico.database

import androidx.lifecycle.LiveData

class ContatoRepository(private val contatoDAO: ContatoDAO){

    val readAllData: LiveData<List<Contato>> = contatoDAO.loadContact()

    suspend fun addContact(contato: Contato){
        contatoDAO.saveContact(contato)
    }

}