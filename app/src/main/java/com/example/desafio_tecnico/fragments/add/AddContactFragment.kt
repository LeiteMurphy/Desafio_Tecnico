package com.example.desafio_tecnico.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.desafio_tecnico.API.CEPJson
import com.example.desafio_tecnico.API.RetrofitBuilder
import com.example.desafio_tecnico.R
import com.example.desafio_tecnico.databinding.FragmentAddContactBinding
import com.example.desafio_tecnico.fragments.update.UpdateFragmentArgs
import com.example.desafio_tecnico.model.Contato
import com.example.desafio_tecnico.util.MaskEditUtil
import com.example.desafio_tecnico.viewmodel.ContatoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val BASE_URL = "https://viacep.com.br/"

class AddContactFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentAddContactBinding
    private lateinit var mContatoViewModel: ContatoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddContactBinding.inflate(LayoutInflater.from(context))


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        mContatoViewModel = ViewModelProvider(this).get(ContatoViewModel::class.java)
        binding.editTextCep.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length == 8){
                    requestCep(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.btnCadastrar.setOnClickListener{
            insertIntoDatabase()
        }
        return binding.root
    }
    private fun insertIntoDatabase(){
        val contatoNome = binding.editTextNomeFragment.text.toString()
        val contatoTelefone = binding.editTextTelefone.text.toString()
        val contatoNascimento = binding.editTextDataNascimento.text.toString()
        val contatoCep = binding.editTextCep.text.toString()
        val contatoEstado = binding.editTextEstado.text.toString()
        val contatoCidade = binding.editTextCidade.text.toString()
        val contatoBairro = binding.editTextBairro.text.toString()
        val contatoLogradouro = binding.editTextLogradouro.text.toString()
        val contatoNumeroResidencia = binding.editTextNumeroResidencia.text.toString()

        if(inputCheck(contatoNome, contatoTelefone, contatoNascimento, contatoCep, contatoEstado, contatoCidade, contatoBairro, contatoLogradouro, contatoNumeroResidencia)){
            //Creating contact object
            val contato = Contato(0, contatoNome, contatoTelefone, contatoNascimento, contatoCep, contatoEstado, contatoCidade, contatoBairro, contatoLogradouro, contatoNumeroResidencia)
            //Adding to database
            mContatoViewModel.addContact((contato))
            Toast.makeText(requireContext(),"Contato adicionado com sucesso!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addContactFragment_to_listFragment)

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
        contatoNumeroResidencia: String,
    ) : Boolean{
        return !( TextUtils.isEmpty(contatoNome)
                || TextUtils.isEmpty(contatoTelefone)
                || TextUtils.isEmpty(contatoNascimento)
                || TextUtils.isEmpty(contatoCep)
                || TextUtils.isEmpty(contatoEstado)
                || TextUtils.isEmpty(contatoCidade)
                || TextUtils.isEmpty(contatoBairro)
                || TextUtils.isEmpty(contatoLogradouro)
                || TextUtils.isEmpty(contatoNumeroResidencia))
    }

    private fun requestCep(cep: String) {
        val call = RetrofitBuilder().apiRetrofitService().getCep(cep)
        binding.editTextCep.addTextChangedListener(MaskEditUtil.mask(binding.editTextCep))

        call.enqueue(object : Callback<CEPJson> {

            override fun onResponse(call: Call<CEPJson>, response: Response<CEPJson>) {

                response.let {
                    val ceps: CEPJson? = it.body()
                    binding.editTextEstado.setText(ceps?.uf)
                    binding.editTextCidade.setText(ceps?.localidade)
                    binding.editTextBairro.setText(ceps?.bairro)
                    binding.editTextLogradouro.setText(ceps?.logradouro)
                }
            }
            override fun onFailure(call: Call<CEPJson>?, t: Throwable?) {
                Log.e("Erro", t?.message.toString())
            }
        })
    }
}