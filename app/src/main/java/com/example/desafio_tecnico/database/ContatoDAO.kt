package com.example.desafio_tecnico.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ContatoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contato: Contato)

    @Query("SELECT * FROM contact_table ORDER BY id ASC")
    fun loadContact(): LiveData<List<Contato>>

}
