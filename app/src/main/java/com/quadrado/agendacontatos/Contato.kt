package com.quadrado.agendacontatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contato(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var nome: String,
    var telefone: String,
    var email: String,
    var cep: String,
    var rua: String,
    var numero: String,
    var cidade: String,
    var estado: String
)