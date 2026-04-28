package activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import adapter.CategoryAdapter
import adapter.FoodAdapter
import com.example.foodmate.R
import com.example.foodmate.databinding.ActivityHomeBinding
import model.Category
import model.FoodData

class HomeActivty : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCategories()
        setupPopularFoods()
        setupFastFoods()
        setupSearch()
        setupBottomNav()
    }

    private fun setupCategories() {
        val names = FoodData.getCategories()
        val emojis = FoodData.getCategoryEmojis()
        val categories = names.mapIndexed { i, name ->
            Category(name = name, emoji = emojis.getOrElse(i) { "🍽️" }, isSelected = i == 0)
        }

        val adapter = CategoryAdapter(this, categories) { position ->
            val selected = categories[position].name
            val filtered = FoodData.filterByCategory(selected)
            (binding.rvPopularFoods.adapter as? FoodAdapter)?.updateData(filtered)
        }

        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = adapter
    }

    private fun setupPopularFoods() {
        val foods = FoodData.getPopularFoods()
        val adapter = FoodAdapter(
            context = this,
            foods = foods,
            isHorizontal = true,
            onItemClick = { food ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("food", food)
                startActivity(intent)
            },
            onAddClick = { food ->
                model.CartManager.addItem(food)
            }
        )
        binding.rvPopularFoods.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularFoods.adapter = adapter
    }

    private fun setupFastFoods() {
        val foods = FoodData.getFastFoods()
        val adapter = FoodAdapter(
            context = this,
            foods = foods,
            isHorizontal = true,
            onItemClick = { food ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("food", food)
                startActivity(intent)
            },
            onAddClick = { food ->
                model.CartManager.addItem(food)
            }
        )
        binding.rvFastFoods.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFastFoods.adapter = adapter
    }

    private fun setupSearch() {
        binding.etHomeSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.isNotEmpty()) {
                    val filtered = FoodData.getAllFoods().filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.restaurant.contains(query, ignoreCase = true)
                    }
                    (binding.rvPopularFoods.adapter as? FoodAdapter)?.updateData(filtered)
                } else {
                    (binding.rvPopularFoods.adapter as? FoodAdapter)?.updateData(FoodData.getPopularFoods())
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupBottomNav() {
        binding.bottomNav.navHome.setOnClickListener { /* already in home */ }
        binding.bottomNav.navSearch.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
        binding.bottomNav.navCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        binding.bottomNav.navProfile.setOnClickListener { /* TODO: ProfileActivity */ }

        binding.tvSeeAllPopular.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
        binding.tvSeeAllFast.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh cart badge jika ada
    }
}
