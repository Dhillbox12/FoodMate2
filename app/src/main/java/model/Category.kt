package model

data class Category(
    val name: String,
    val emoji: String,
    var isSelected: Boolean = false
)