package com.quadrado.agendacontatos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContatoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setTitle(R.string.app_name)

        listarContatos()

        val salvarContato = Intent(this, SalvarNovoContato::class.java)
        val fab_adicionarContato = findViewById<FloatingActionButton>(R.id.fab_adicionarContato).setOnClickListener() {
            startActivity(salvarContato)
        }

    }

    override fun onResume() {
        super.onResume()
        listarContatos()
    }

    private fun listarContatos() {
        val listaContatos = findViewById<RecyclerView>(R.id.rv_Contatos)
        listaContatos.layoutManager = LinearLayoutManager(this)

        adapter = ContatoAdapter(mutableListOf())
        listaContatos.adapter = adapter

        if (adapter.itemCount == 0) {
            Toast.makeText(this, "Essa lista está vazia.", Toast.LENGTH_LONG).show()
        }
    }
}