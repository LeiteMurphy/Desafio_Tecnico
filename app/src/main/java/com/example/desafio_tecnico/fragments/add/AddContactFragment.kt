package com.example.desafio_tecnico.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.desafio_tecnico.R
import com.example.desafio_tecnico.database.ContatoViewModel

class AddContactFragment : Fragment() {

    private lateinit var mContatoViewModel: ContatoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        mContatoViewModel = ViewModelProvider(this).get(ContatoViewModel::class.java)

        view.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
    }
}