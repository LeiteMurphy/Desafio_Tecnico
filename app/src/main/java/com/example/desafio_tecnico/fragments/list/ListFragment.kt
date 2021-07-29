package com.example.desafio_tecnico.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import com.example.desafio_tecnico.R
import com.example.desafio_tecnico.databinding.ActivityContactListBinding
import com.example.desafio_tecnico.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentListBinding.inflate(layoutInflater)
        val view = binding.root
        binding.fabCreateContact.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addContactFragment)
        }
        setContentView(view)

    }

    private fun setContentView(view: View) {
    }
}