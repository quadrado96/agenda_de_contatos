package com.quadrado.agendacontatos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContatoDAO {

    @Insert
    fun salvar(c: Contato)

    @Update
    fun atualizar(c: Contato)

    @Delete
    fun apagar(c: Contato)

    @Query("SELECT * FROM Contato ORDER BY nome")
    fun listarContatos(): List<Contato>

}