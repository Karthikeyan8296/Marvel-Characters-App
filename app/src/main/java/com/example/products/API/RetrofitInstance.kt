package com.example.products.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.simplifiedcoding.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ProductAPI = getInstance().create(ProductAPI::class.java)
}