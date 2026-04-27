package model

import java.io.Serializable

data class Food(
    val id: Int,
    val name: String,
    val emoji: String,
    val bgColor: Int,          // Resource color ID
    val price: Int,            // in Rupiah
    val rating: Float,
    val reviewCount: Int,
    val restaurant: String,
    val category: String,
    val estimatedTime: String,
    val description: String,
    val tag1: String,
    val tag2: String,
    val isPopular: Boolean = false
) : Serializable