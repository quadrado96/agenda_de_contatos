package com.quadrado.agendacontatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContatoAdapter(private val listaContatos: MutableList<Contato>,
                     private val onLongClick: (Contato) -> Unit) :
    RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder>() {

    class ContatoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNome = view.findViewById<TextView>(R.id.tv_Nome)
        val tvTelefone = view.findViewById<TextView>(R.id.tv_Telefone)
        val tvEmail = view.findViewById<TextView>(R.id.tv_Email)
        val tvEndereco = view.findViewById<TextView>(R.id.tv_Endereco)
        val tvCep = view.findViewById<TextView>(R.id.tv_Cep)
        val imgContato = view.findViewById<ImageView>(R.id.img_Contatoo)

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

        if (contato.nome.equals("Brunno") ||
            contato.nome.equals("Brunno Quadrado")) {
            holder.imgContato.setImageResource(R.drawable.batman)
        }

        holder.itemView.setOnLongClickListener {
            onLongClick(contato)
            true
        }

    }

    override fun getItemCount(): Int {
        return listaContatos.size
    }

    fun listarContatos(novaLista: List<Contato>) {
        listaContatos.clear()
        listaContatos.addAll(novaLista)
        notifyDataSetChanged()
    }

}