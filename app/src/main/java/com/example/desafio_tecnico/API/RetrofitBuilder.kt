package com.example.desafio_tecnico.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    fun init() {
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/ws/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiRetrofitService(): ApiRequest {
        return retrofit.create(ApiRequest::class.java)
    }
}