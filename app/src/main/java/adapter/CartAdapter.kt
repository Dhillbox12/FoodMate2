package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmate.R
import model.CartItem
import model.FoodData

class CartAdapter(
    private val context: Context,
    private val cartItems: MutableList<CartItem>,
    private val onQuantityChange: (CartItem, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmoji: TextView = itemView.findViewById(R.id.tvCartEmoji)
        val tvName: TextView = itemView.findViewById(R.id.tvCartItemName)
        val tvRestaurant: TextView = itemView.findViewById(R.id.tvCartRestaurant)
        val tvPrice: TextView = itemView.findViewById(R.id.tvCartItemPrice)
        val tvQty: TextView = itemView.findViewById(R.id.tvCartQty)
        val btnMinus: View = itemView.findViewById(R.id.btnCartMinus)
        val btnPlus: View = itemView.findViewById(R.id.btnCartPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cartItems[position]
        holder.tvEmoji.text = item.food.emoji
        holder.tvName.text = item.food.name
        holder.tvRestaurant.text = item.food.restaurant
        holder.tvPrice.text = FoodData.formatPrice(item.totalPrice)
        holder.tvQty.text = item.quantity.toString()

        holder.tvEmoji.setBackgroundColor(context.resources.getColor(item.food.bgColor, null))

        holder.btnMinus.setOnClickListener {
            onQuantityChange(item, item.quantity - 1)
        }
        holder.btnPlus.setOnClickListener {
            onQuantityChange(item, item.quantity + 1)
        }
    }

    override fun getItemCount() = cartItems.size

    fun notifyItemUpdated(position: Int) {
        notifyItemChanged(position)
    }
}