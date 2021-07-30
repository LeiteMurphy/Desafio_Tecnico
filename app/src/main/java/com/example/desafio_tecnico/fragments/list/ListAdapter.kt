package com.example.desafio_tecnico.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_tecnico.R
import com.example.desafio_tecnico.databinding.ActivityContactListBinding
import com.example.desafio_tecnico.databinding.FragmentAddContactBinding
import com.example.desafio_tecnico.databinding.FragmentListBinding
import com.example.desafio_tecnico.model.Contato

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var contactList = emptyList<Contato>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_contact_list, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.itemView.findViewById<TextView>(R.id.tv_contact_name).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.tv_contact_number).text = currentItem.telefone

        holder.itemView.findViewById<ConstraintLayout>(R.id.list_item).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setData(contato: List<Contato>){
        this.contactList = contato
        notifyDataSetChanged()
    }
}