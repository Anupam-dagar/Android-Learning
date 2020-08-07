package com.example.mvvmapp.data

data class Item(val itemName: String, val itemCategory: String) {
    override fun toString(): String {
        return "$itemName - $itemCategory"
    }
}