package com.example.desafio_tecnico.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.desafio_tecnico.model.Contato

@Database(entities = [Contato::class], version = 2, exportSchema = false)
abstract class ContatoDatabase : RoomDatabase(){

    abstract fun contatoDao(): ContatoDAO

    companion object{
        @Volatile
        private var INSTANCE: ContatoDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact_table ADD COLUMN telefone TEXT")
            }
        }

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
                ).addMigrations(MIGRATION_1_2).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}