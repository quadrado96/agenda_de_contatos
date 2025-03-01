package com.quadrado.agendacontatos

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = [Contato::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun ContatoDAO(): ContatoDAO

    companion object {

        private var database: Database? = null
        private val DATABASE = "CONTATOS"

        fun getInstance(context: Context): Database {
            if (database == null) {
                database = criaBanco(context)
            }
            return database!!
        }

        private fun criaBanco(context: Context): Database? {
            return Room.databaseBuilder(
                context, Database::class.java, DATABASE)
                .allowMainThreadQueries().build()
        }
    }


}