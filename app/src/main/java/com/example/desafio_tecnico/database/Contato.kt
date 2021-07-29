package com.example.desafio_tecnico.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contato(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val nascimento: String,
    val cep: String,
    val estado: String,
    val cidade: String,
    val bairro: String,
    val logradouro: String,
    val numeroResidencia: String
)

//class ContatoBuilder{
//    var name: String = ""
//    var nascimento: String = ""
//    var cep: String = ""
//    var estado: String = ""
//    var cidade: String = ""
//    var bairro: String = ""
//    var logradouro: String = ""
//    var numeroResidencia: String  = ""
//
//    fun build() : Contato = Contato(name, nascimento, cep, estado, cidade, bairro, logradouro, numeroResidencia)
//}