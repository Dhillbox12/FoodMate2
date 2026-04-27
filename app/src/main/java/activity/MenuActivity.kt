package activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import adapter.FoodAdapter
import com.example.foodmate.R
import com.example.foodmate.databinding.ActivityMenuBinding
import model.FoodData

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var foodAdapter: FoodAdapter
    private var currentCategory = "Semua"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupChips()
        setupSearch()
        setupBottomNav()
    }

    private fun setupRecyclerView() {
        val foods = FoodData.getAllFoods()
        foodAdapter = FoodAdapter(
            context = this,
            foods = foods,
            isHorizontal = false,
            onItemClick = { food ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("food", food)
                startActivity(intent)
            },
            onAddClick = { food ->
                model.CartManager.addItem(food)
            }
        )
        binding.rvMenuGrid.layoutManager = GridLayoutManager(this, 2)
        binding.rvMenuGrid.adapter = foodAdapter
        updateCount(foods.size)
    }

    private fun setupChips() {
        val chips = mapOf(
            binding.chipAll to "Semua",
            binding.chipBurger to "Burger",
            binding.chipPizza to "Pizza",
            binding.chipMie to "Mie",
            binding.chipSushi to "Sushi",
            binding.chipAyam to "Ayam",
            binding.chipDessert to "Dessert"
        )

        chips.forEach { (chip, category) ->
            chip.setOnClickListener {
                currentCategory = category
                // Reset semua chip ke unselected
                chips.keys.forEach { c ->
                    c.setBackgroundResource(R.drawable.bg_chip_unselected)
                    c.setTextColor(resources.getColor(R.color.text_secondary, null))
                }
                // Set chip yang dipilih
                chip.setBackgroundResource(R.drawable.bg_chip_selected)
                chip.setTextColor(resources.getColor(R.color.text_white, null))

                val filtered = FoodData.filterByCategory(category)
                foodAdapter.updateData(filtered)
                updateCount(filtered.size)
            }
        }
    }

    private fun setupSearch() {
        binding.etMenuSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                val base = FoodData.filterByCategory(currentCategory)
                val filtered = if (query.isNotEmpty()) {
                    base.filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.restaurant.contains(query, ignoreCase = true)
                    }
                } else base
                foodAdapter.updateData(filtered)
                updateCount(filtered.size)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun updateCount(count: Int) {
        binding.tvMenuCount.text = getString(R.string.menu_count, count)
    }

    private fun setupBottomNav() {
        binding.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivty::class.java))
            finish()
        }
        binding.bottomNav.navSearch.setOnClickListener { /* already in menu */ }
        binding.bottomNav.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        binding.bottomNav.navProfile.setOnClickListener { /* TODO: ProfileActivity */ }
    }
}
