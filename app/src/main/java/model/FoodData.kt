package model

import com.example.foodmate.R

object FoodData {
    fun getAllFoods(): List<Food> = listOf(
        Food(
            id = 1,
            name = "Burger Crispy Spesial",
            emoji = "🍔",
            bgColor = R.color.bg_yellow,
            price = 35000,
            rating = 4.9f,
            reviewCount = 324,
            restaurant = "BurgerMate",
            category = "Burger",
            estimatedTime = "20–30 mnt",
            description = "Burger crispy premium dengan patty daging sapi pilihan 150gr, saus rahasia spesial, selada segar, tomat, dan keju cheddar meleleh. Disajikan dengan kentang goreng renyah. Nikmati cita rasa autentik yang tak terlupakan!",
            tag1 = "Terlaris",
            tag2 = "Halal",
            isPopular = true
        ),
        Food(
            id = 2,
            name = "Pizza Margherita",
            emoji = "🍕",
            bgColor = R.color.bg_orange,
            price = 55000,
            rating = 4.7f,
            reviewCount = 198,
            restaurant = "Pizza House",
            category = "Pizza",
            estimatedTime = "25–35 mnt",
            description = "Pizza klasik Italia dengan saus tomat San Marzano, mozzarella segar, dan daun basil aromatik. Dipanggang di oven batu pada suhu 400°C untuk mendapatkan tepian crispy yang sempurna.",
            tag1 = "Best Seller",
            tag2 = "Vegetarian",
            isPopular = true
        ),
        Food(
            id = 3,
            name = "Mie Goreng Spesial",
            emoji = "🍜",
            bgColor = R.color.bg_green,
            price = 28000,
            rating = 4.8f,
            reviewCount = 512,
            restaurant = "Warung Mie",
            category = "Mie",
            estimatedTime = "15–20 mnt",
            description = "Mie goreng dengan bumbu rahasia turun-temurun, ayam suwir, telur, sayuran segar, dan taburan bawang goreng crispy. Tersedia level kepedasan 1–5 sesuai selera Anda.",
            tag1 = "Pedas",
            tag2 = "Halal",
            isPopular = true
        ),
        Food(
            id = 4,
            name = "Salmon Sushi Set",
            emoji = "🍣",
            bgColor = R.color.bg_blue,
            price = 85000,
            rating = 4.9f,
            reviewCount = 276,
            restaurant = "Sushi Corner",
            category = "Sushi",
            estimatedTime = "30–40 mnt",
            description = "Set sushi premium berisi 8 pcs nigiri salmon segar, 6 pcs california roll, dan 4 pcs salmon gunkan. Disajikan dengan wasabi, jahe acar, dan kecap asin Jepang pilihan.",
            tag1 = "Premium",
            tag2 = "Fresh",
            isPopular = true
        ),
        Food(
            id = 5,
            name = "Ayam Geprek Level 5",
            emoji = "🍗",
            bgColor = R.color.bg_pink,
            price = 32000,
            rating = 4.6f,
            reviewCount = 445,
            restaurant = "Geprek Mania",
            category = "Ayam",
            estimatedTime = "15–25 mnt",
            description = "Ayam goreng krispy tepung spesial yang digeprek dan dilumuri sambal bawang super pedas level 5. Disajikan dengan nasi putih hangat, lalapan segar, dan tempe goreng.",
            tag1 = "Super Pedas",
            tag2 = "Halal"
        ),
        Food(
            id = 6,
            name = "Red Velvet Cake",
            emoji = "🧁",
            bgColor = R.color.bg_purple,
            price = 42000,
            rating = 4.8f,
            reviewCount = 189,
            restaurant = "Sweet Tooth",
            category = "Dessert",
            estimatedTime = "10–15 mnt",
            description = "Kue red velvet premium berlapis cream cheese frosting yang lembut dan creamy. Dibuat dari bahan pilihan dengan tekstur yang moist dan warna merah yang menggoda. Sempurna untuk dessert atau hadiah.",
            tag1 = "Bestseller",
            tag2 = "Recommended"
        ),
        Food(
            id = 7,
            name = "Caesar Salad Bowl",
            emoji = "🥗",
            bgColor = R.color.bg_green,
            price = 48000,
            rating = 4.5f,
            reviewCount = 134,
            restaurant = "Greens & Co",
            category = "Salad",
            estimatedTime = "10–15 mnt",
            description = "Salad Caesar segar dengan romaine lettuce renyah, crouton homemade, parmesan shaved, dan saus Caesar buatan sendiri. Pilihan sempurna untuk makan siang sehat dan mengenyangkan.",
            tag1 = "Healthy",
            tag2 = "Low Calorie"
        )
    )

    fun getPopularFoods() = getAllFoods().filter { it.isPopular }

    fun getFastFoods() = getAllFoods().filter {
        it.estimatedTime.startsWith("10") || it.estimatedTime.startsWith("15")
    }

    fun getCategories() = listOf("Semua", "Burger", "Pizza", "Mie", "Sushi", "Ayam", "Dessert", "Salad")

    fun getCategoryEmojis() = listOf("🍽️", "🍔", "🍕", "🍜", "🍣", "🍗", "🧁", "🥗")

    fun filterByCategory(category: String): List<Food> {
        if (category == "Semua") return getAllFoods()
        return getAllFoods().filter { it.category == category }
    }

    fun formatPrice(price: Int): String {
        return "Rp ${String.format("%,d", price).replace(",", ".")}"
    }
}
