package com.example.desafio_tecnico

import com.example.desafio_tecnico.API.CEPJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {
    @GET("{CEP}/json/")
    fun getCep(@Path("CEP") cep: String): Call<CEPJson>

    @GET("{uf}/{cidade}/{endereco}/{localidade}/json/")
    fun getCidadeEstadoEndereco(
        @Path("uf") estado: String,
        @Path("cidade") cidade: String,
        @Path("localidade") localidade: String,
        @Path("endereco") endereco: String
    ): Call<List<CEPJson>>


}