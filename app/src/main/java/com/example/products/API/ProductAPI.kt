package com.example.products.API

import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET("demos/marvel/")
    suspend fun getProducts(): Response<List<ProductResponse>>
}