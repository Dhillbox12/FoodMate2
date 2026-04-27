package model

import java.io.Serializable

data class CartItem(
    val food: Food,
    var quantity: Int = 1
) : Serializable {
    val totalPrice: Int get() = food.price * quantity
}