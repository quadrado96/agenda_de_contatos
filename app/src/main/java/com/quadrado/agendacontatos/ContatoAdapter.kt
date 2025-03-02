package com.quadrado.agendacontatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContatoAdapter(private val listaContatos: MutableList<Contato>) :
    RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>() {

    class ContatoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNome = view.findViewById<TextView>(R.id.tv_Nome)
        val tvTelefone = view.findViewById<TextView>(R.id.tv_Telefone)
        val tvEmail = view.findViewById<TextView>(R.id.tv_Email)
        val tvEndereco = view.findViewById<TextView>(R.id.tv_Endereco)
        val tvCep = view.findViewById<TextView>(R.id.tv_Cep)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_contatos, parent, false)
        return ContatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = listaContatos[position]

        val stringEndereco = "${contato.rua} ${contato.numero} - ${contato.cidade} ${contato.estado}"
        holder.tvNome.text = contato.nome
        holder.tvTelefone.text = contato.telefone
        holder.tvEmail.text = contato.email
        holder.tvCep.text = contato.cep
        holder.tvEndereco.text = stringEndereco
    }

    override fun getItemCount(): Int {
        return listaContatos.size
    }

    fun atualizarLista(novaLista: List<Contato>) {
        listaContatos.clear()
        listaContatos.addAll(novaLista)
        notifyDataSetChanged()
    }

}