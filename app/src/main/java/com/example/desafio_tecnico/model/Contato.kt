package com.example.desafio_tecnico.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contact_table")
data class Contato(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val telefone: String,
    val nascimento: String,
    val cep: String,
    val estado: String,
    val cidade: String,
    val bairro: String,
    val logradouro: String,
    val numeroResidencia: String
): Parcelable