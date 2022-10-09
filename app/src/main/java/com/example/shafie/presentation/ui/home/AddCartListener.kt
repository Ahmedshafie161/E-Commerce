package com.example.shafie.presentation.ui.home

import com.example.shafie.data.remote.dtos.ProductDto

interface AddCartListener {

   fun addItemToCart(productDetails: ProductDto)

}