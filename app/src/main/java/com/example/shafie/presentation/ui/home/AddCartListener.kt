package com.example.shafie.presentation.ui.home

import com.example.shafie.network.modelapi.OnlineEntity

interface AddCartListener {

   fun addItemToCart(productDetails: OnlineEntity)

}