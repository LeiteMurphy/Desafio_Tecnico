package com.example.desafio_tecnico.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio_tecnico.R
import com.example.desafio_tecnico.databinding.ActivityContactListBinding
import com.example.desafio_tecnico.viewmodel.ContatoViewModel
import com.example.desafio_tecnico.databinding.FragmentListBinding
import com.example.desafio_tecnico.fragments.update.UpdateFragmentArgs

class ListFragment : Fragment() {

    private lateinit var mContatoViewModel: ContatoViewModel
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        //RecyclerView
        val adapter = ListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ContatoViewModel
        mContatoViewModel = ViewModelProvider(this).get(ContatoViewModel::class.java)
        mContatoViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabCreateContact.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addContactFragment)
        }
    }
}

