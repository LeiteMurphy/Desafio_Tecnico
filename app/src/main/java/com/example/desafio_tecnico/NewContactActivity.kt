package com.example.desafio_tecnico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.desafio_tecnico.API.CEPJson
import com.example.desafio_tecnico.databinding.ActivityNewContactBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val BASE_URL = "https://viacep.com.br/"

class NewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContactBinding


    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_contact)
        binding.editTextCep.addTextChangedListener(object: TextWatcher{
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

    }

    private fun requestCep(cep: String) {
        val call = RetrofitBuilder().apiRetrofitService().getCep(cep)

        call.enqueue(object : Callback<CEPJson> {

            override fun onResponse(call: Call<CEPJson>, response: Response<CEPJson>) {

                response.let {
                    val ceps: CEPJson? = it.body()
                    Log.i("CEP", ceps.toString())
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