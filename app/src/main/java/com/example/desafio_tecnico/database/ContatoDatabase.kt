package com.example.desafio_tecnico.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [Contato::class], version = 1, exportSchema = false)
abstract class ContatoDatabase : RoomDatabase(){

    abstract fun contatoDao(): ContatoDAO

    companion object{
        @Volatile
        private var INSTANCE: ContatoDatabase? = null

        fun getDatabase(context: Context): ContatoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContatoDatabase::class.java,
                    "contact_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}