package com.example.sqlshoppinglist.models

data class Product(
    val _id: Int,
    val name: String,
    val count: Int,
    val list_id: Int,
    val checked: Boolean,
    val count_type: Int
)