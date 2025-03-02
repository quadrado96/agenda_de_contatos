package com.quadrado.agendacontatos

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SalvarNovoContato : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salvar_novo_contato)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setTitle(R.string.dados_do_contato)

        val nome = findViewById<EditText>(R.id.et_Nome)
        val telefone = findViewById<EditText>(R.id.et_Telefone)
        val email = findViewById<EditText>(R.id.et_Email)
        val cep = findViewById<EditText>(R.id.et_Cep)
        val rua = findViewById<EditText>(R.id.et_Rua)
        val numero = findViewById<EditText>(R.id.et_Numero)
        val cidade = findViewById<EditText>(R.id.et_Cidade)
        val estado = findViewById<EditText>(R.id.et_Estado)

        val salvarContato = findViewById<FloatingActionButton>(R.id.fab_salvarContato).setOnClickListener() {

            val contato = Contato(
                null,
                nome.text.toString(),
                telefone.text.toString(),
                email.text.toString(),
                cep.text.toString(),
                rua.text.toString(),
                numero.text.toString(),
                cidade.text.toString(),
                estado.text.toString()
            )

            when {
                telefone.text.toString().length != 11 -> {
                    showToast("O telefone deve ter 11 dígitos!")
                    return@setOnClickListener
                }

                cep.text.toString().length != 8 -> {
                    showToast("O CEP deve ter 8 dígitos!")
                    return@setOnClickListener
                }

                estado.text.toString().length != 2 -> {
                    showToast("O estado deve ter 2 caracteres (Apenas a sigla)!")
                    return@setOnClickListener
                }

                nome.text.toString().isBlank() ||
                        telefone.text.toString().isBlank() ||
                        email.text.toString().isBlank() ||
                        cep.text.toString().isBlank() ||
                        rua.text.toString().isBlank() ||
                        numero.text.toString().isBlank() ||
                        cidade.text.toString().isBlank() ||
                        estado.text.toString().isBlank() -> {
                    showToast("Todos os campos são obrigatórios!")
                    return@setOnClickListener
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                Database.getInstance(this@SalvarNovoContato)!!.ContatoDAO().salvar(contato)

                withContext(Dispatchers.Main) {
                    showToast("Contato salvo!")
                    finish()
                }
            }

        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}