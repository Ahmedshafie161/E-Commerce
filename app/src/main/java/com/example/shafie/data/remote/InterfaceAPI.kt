package com.example.shafie.data.remote

import com.example.shafie.data.remote.dtos.ProductDto
import com.example.shafie.data.remote.dtos.ProjectObject
import retrofit2.http.GET

interface InterfaceAPI {

    @GET("/nancymadan/assignment/db")
    suspend fun getProductsFromApi(): ProjectObject
}