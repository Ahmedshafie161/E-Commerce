package com.example.shafie.network

import com.example.shafie.network.modelapi.DataClassAPI
import retrofit2.Response
import retrofit2.http.GET

interface InterfaceAPI {

    @GET("/nancymadan/assignment/db")
    suspend fun getProductsFromApi():Response<DataClassAPI>
}