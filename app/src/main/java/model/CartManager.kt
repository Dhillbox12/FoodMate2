package model

object CartManager {
    val items = mutableListOf<CartItem>()

    fun addItem(food: Food, quantity: Int = 1) {
        val existing = items.find { it.food.id == food.id }
        if (existing != null) {
            existing.quantity += quantity
        } else {
            items.add(CartItem(food, quantity))
        }
    }

    fun removeItem(foodId: Int) {
        items.removeAll { it.food.id == foodId }
    }

    fun updateQuantity(foodId: Int, quantity: Int) {
        val item = items.find { it.food.id == foodId }
        if (item != null) {
            if (quantity <= 0) removeItem(foodId)
            else item.quantity = quantity
        }
    }

    fun getTotalItems(): Int = items.sumOf { it.quantity }

    fun getSubtotal(): Int = items.sumOf { it.totalPrice }

    fun clear() = items.clear()
}