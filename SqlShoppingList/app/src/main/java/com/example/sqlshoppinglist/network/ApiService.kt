package com.example.sqlshoppinglist.network

import com.example.sqlshoppinglist.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}