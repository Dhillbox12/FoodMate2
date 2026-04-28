package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmate.R
import model.Food
import model.FoodData

class FoodAdapter(
    private val context: Context,
    private var foods: List<Food>,
    private val isHorizontal: Boolean = false,
    private val onItemClick: (Food) -> Unit,
    private val onAddClick: ((Food) -> Unit)? = null
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardFood: CardView = itemView.findViewById(R.id.cardFood)
        val tvEmoji: TextView = itemView.findViewById(R.id.tvFoodEmoji)
        val tvName: TextView = itemView.findViewById(R.id.tvFoodName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvFoodPrice)
        val tvRating: TextView = itemView.findViewById(R.id.tvFoodRating)
        val tvRestaurant: TextView? = itemView.findViewById(R.id.tvRestaurant)
        val btnAdd: View? = itemView.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = if (isHorizontal) R.layout.item_food_horizontal else R.layout.item_food_grid
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        holder.tvEmoji.text = food.emoji
        holder.tvName.text = food.name
        holder.tvPrice.text = FoodData.formatPrice(food.price)
        holder.tvRating.text = context.getString(R.string.food_rating, food.rating.toString())
        holder.tvRestaurant?.text = context.getString(R.string.food_restaurant, food.restaurant)

        holder.tvEmoji.setBackgroundColor(context.resources.getColor(food.bgColor, null))

        holder.cardFood.setOnClickListener { onItemClick(food) }
        holder.btnAdd?.setOnClickListener { onAddClick?.invoke(food) }
    }

    override fun getItemCount() = foods.size

    fun updateData(newFoods: List<Food>) {
        val oldSize = foods.size
        val newSize = newFoods.size
        foods = newFoods
        val commonSize = minOf(oldSize, newSize)
        if (commonSize > 0) notifyItemRangeChanged(0, commonSize)
        if (newSize > oldSize) notifyItemRangeInserted(oldSize, newSize - oldSize)
        if (newSize < oldSize) notifyItemRangeRemoved(newSize, oldSize - newSize)
    }
}
