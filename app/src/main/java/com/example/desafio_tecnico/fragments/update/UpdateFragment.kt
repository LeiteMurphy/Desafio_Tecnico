package com.example.desafio_tecnico.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.desafio_tecnico.R
import com.example.desafio_tecnico.databinding.FragmentListBinding
import com.example.desafio_tecnico.databinding.FragmentUpdateBinding
import com.example.desafio_tecnico.model.Contato
import com.example.desafio_tecnico.viewmodel.ContatoViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mContatoViewModel: ContatoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mContatoViewModel = ViewModelProvider(this).get(ContatoViewModel::class.java)

        binding.updateEtNome.setText(args.currentContact.name)
        binding.updateEtTelefone.setText(args.currentContact.telefone)
        binding.updateEtDataNascimento.setText(args.currentContact.nascimento)
        binding.updateEtCep.setText(args.currentContact.cep)
        binding.updateEtEstado.setText(args.currentContact.estado)
        binding.updateEtCidade.setText(args.currentContact.cidade)
        binding.updateEtBairro.setText(args.currentContact.bairro)
        binding.updateEtLogradouro.setText(args.currentContact.logradouro)
        binding.updateEtNumeroResidencia.setText(args.currentContact.numeroResidencia)

        binding.btnAtualizar.setOnClickListener {
            updateContact()
        }
        //Add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateContact(){
        val contatoNome = binding.updateEtNome.text.toString()
        val contatoTelefone = binding.updateEtTelefone.text.toString()
        val contatoNascimento = binding.updateEtDataNascimento.text.toString()
        val contatoCep = binding.updateEtCep.text.toString()
        val contatoEstado = binding.updateEtEstado.text.toString()
        val contatoCidade = binding.updateEtCidade.text.toString()
        val contatoBairro = binding.updateEtBairro.text.toString()
        val contatoLogradouro = binding.updateEtLogradouro.text.toString()
        val contatoNumeroResidencia = binding.updateEtNumeroResidencia.text.toString()

        if(inputCheck(contatoNome, contatoTelefone, contatoNascimento, contatoCep, contatoEstado, contatoCidade, contatoBairro, contatoLogradouro, contatoNumeroResidencia)){
            //Create Contact Object
            val updateContato = Contato(args.currentContact.id, contatoNome, contatoTelefone, contatoNascimento, contatoCep, contatoEstado, contatoCidade, contatoBairro, contatoLogradouro, contatoNumeroResidencia)
            //Update Current Contact
            mContatoViewModel.updateContact(updateContato)
            Toast.makeText(requireContext(),"Contato atualizado com sucesso!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(
        contatoNome: String,
        contatoTelefone: String,
        contatoNascimento: String,
        contatoCep: String,
        contatoEstado: String,
        contatoCidade: String,
        contatoBairro: String,
        contatoLogradouro: String,
        contatoNumeroResidencia: String,) : Boolean{
        return !( TextUtils.isEmpty(contatoNome)
                && TextUtils.isEmpty(contatoTelefone)
                && TextUtils.isEmpty(contatoNascimento)
                && TextUtils.isEmpty(contatoCep)
                && TextUtils.isEmpty(contatoEstado)
                && TextUtils.isEmpty(contatoCidade)
                && TextUtils.isEmpty(contatoBairro)
                && TextUtils.isEmpty(contatoLogradouro)
                && TextUtils.isEmpty(contatoNumeroResidencia))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteContact()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteContact() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim"){_,_ ->
            mContatoViewModel.deleteContact(args.currentContact)
            Toast.makeText(requireContext(),"Contato ${args.currentContact.name} deletado com sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Não"){_,_ ->}
        builder.setTitle("Apagar ${args.currentContact.name} ?")
        builder.setMessage("Você tem certeza que gostaria de apagar ${args.currentContact.name} da sua lista de contatos ?")
        builder.create().show()
    }
}