package activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import adapter.CartAdapter
import com.example.foodmate.R
import com.example.foodmate.databinding.ActivityCartBinding
import model.CartManager
import model.FoodData

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    private val deliveryFee = 15000
    private val serviceFee = 5000
    private var discount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupPromoCode()
        setupCheckout()
        setupBottomNav()
        updateSummary()
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(
            context = this,
            cartItems = CartManager.items,
            onQuantityChange = { item, newQty ->
                val position = CartManager.items.indexOf(item)
                CartManager.updateQuantity(item.food.id, newQty)
                if (position >= 0) {
                    cartAdapter.notifyItemChanged(position)
                }
                updateSummary()
            }
        )
        binding.rvCartItems.layoutManager = LinearLayoutManager(this)
        binding.rvCartItems.adapter = cartAdapter
        updateEmptyState()
    }

    private fun updateEmptyState() {
        val isEmpty = CartManager.items.isEmpty()
        binding.layoutEmptyCart.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.rvCartItems.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    private fun updateSummary() {
        val subtotal = CartManager.getSubtotal()
        val total = subtotal + deliveryFee + serviceFee - discount
        val itemCount = CartManager.getTotalItems()

        binding.tvCartSubtitle.text = getString(R.string.cart_subtitle, itemCount)
        binding.tvSubtotal.text = FoodData.formatPrice(subtotal)
        binding.tvCartTotal.text = FoodData.formatPrice(total)
        updateEmptyState()
    }

    private fun setupPromoCode() {
        binding.btnUsePromo.setOnClickListener {
            val code = binding.etPromoCode.text.toString().trim().uppercase()
            when (code) {
                "FOODMATE50" -> {
                    discount = CartManager.getSubtotal() / 2
                    Toast.makeText(this, getString(R.string.promo_foodmate50_success), Toast.LENGTH_SHORT).show()
                }
                "HEMAT10" -> {
                    discount = 10000
                    Toast.makeText(this, getString(R.string.promo_hemat10_success), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    discount = 0
                    Toast.makeText(this, getString(R.string.promo_invalid), Toast.LENGTH_SHORT).show()
                }
            }
            updateSummary()
        }
    }

    private fun setupCheckout() {
        binding.btnCheckout.setOnClickListener {
            if (CartManager.items.isEmpty()) {
                Toast.makeText(this, getString(R.string.cart_empty_warning), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val removedCount = CartManager.items.size
            CartManager.clear()
            cartAdapter.notifyItemRangeRemoved(0, removedCount)
            updateSummary()
            Toast.makeText(this, getString(R.string.order_success), Toast.LENGTH_LONG).show()
        }
    }

    private fun setupBottomNav() {
        binding.bottomNav.navHome.setOnClickListener {
            startActivity(Intent(this, HomeActivty::class.java))
            finish()
        }
        binding.bottomNav.navSearch.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }
        binding.bottomNav.navCart.setOnClickListener { /* already in cart */ }
        binding.bottomNav.navProfile.setOnClickListener { /* TODO: ProfileActivity */ }
    }
}
