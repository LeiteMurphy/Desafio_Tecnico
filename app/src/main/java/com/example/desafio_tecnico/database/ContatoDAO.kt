package com.example.desafio_tecnico.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.desafio_tecnico.model.Contato


@Dao
interface ContatoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contato: Contato)

    @Query("SELECT * FROM contact_table ORDER BY id ASC")
    fun loadContact(): LiveData<List<Contato>>

    @Update
    suspend fun updateContact(contato: Contato)

    @Delete
    suspend fun deleteContact(contato: Contato)

}
