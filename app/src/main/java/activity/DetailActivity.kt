package activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmate.databinding.ActivityDetailBinding
import model.CartManager
import model.Food
import model.FoodData

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var quantity = 1
    private lateinit var food: Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        @Suppress("DEPRECATION")
        food = intent.getSerializableExtra("food") as? Food ?: run {
            finish()
            return
        }

        bindFoodData()
        setupQuantityControls()
        setupButtons()
    }

    private fun bindFoodData() {
        binding.tvDetailEmoji.text = food.emoji
        binding.tvDetailEmoji.setBackgroundColor(resources.getColor(food.bgColor, null))
        binding.tvDetailName.text = food.name
        binding.tvDetailTag1.text = food.tag1
        binding.tvDetailTag2.text = food.tag2
        binding.tvDetailRatingFull.text = "⭐ ${food.rating}  (${food.reviewCount} ulasan)"
        binding.tvDetailTime.text = "⏱ ${food.estimatedTime}"
        binding.tvDetailRestaurant.text = "🏪 ${food.restaurant}"
        binding.tvDetailPrice.text = FoodData.formatPrice(food.price)
        binding.tvDetailDesc.text = food.description
        updateTotalPrice()
    }

    private fun setupQuantityControls() {
        binding.btnQtyMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.tvQtyNumber.text = quantity.toString()
                updateTotalPrice()
            }
        }

        binding.btnQtyPlus.setOnClickListener {
            quantity++
            binding.tvQtyNumber.text = quantity.toString()
            updateTotalPrice()
        }
    }

    private fun updateTotalPrice() {
        val total = food.price * quantity
        binding.tvQtyTotal.text = "Total: ${FoodData.formatPrice(total)}"
    }

    private fun setupButtons() {
        binding.btnDetailBack.setOnClickListener {
            finish()
        }

        binding.btnDetailFav.setOnClickListener {
            val isFav = binding.btnDetailFav.text == "♥"
            binding.btnDetailFav.text = if (isFav) "♡" else "♥"
            binding.btnDetailFavBottom.text = if (isFav) "♡" else "♥"
        }

        binding.btnDetailFavBottom.setOnClickListener {
            binding.btnDetailFav.performClick()
        }

        binding.btnAddToCart.setOnClickListener {
            CartManager.addItem(food, quantity)
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}
