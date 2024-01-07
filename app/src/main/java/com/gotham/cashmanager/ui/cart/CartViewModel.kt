package com.gotham.cashmanager.ui.cart

import android.app.Dialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.gotham.cashmanager.R


import com.gotham.cashmanager.ui.cart.CartItems
import com.gotham.cashmanager.ui.cart.CartItemsAdapter

class CartViewModel : ViewModel() {

    private val _sum = MutableLiveData<Double>().apply {
        value = 0.00
    }
    var sum: LiveData<Double> = _sum

    fun setSum(value: Double) {
        _sum.value = value
    }

}