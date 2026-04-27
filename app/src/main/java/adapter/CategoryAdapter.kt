package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmate.R
import model.Category

class CategoryAdapter(
    private val context: Context,
    private val categories: List<Category>,
    private val onCategoryClick: (Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var selectedPosition = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardCategory: CardView = itemView.findViewById(R.id.cardCategory)
        val tvEmoji: TextView = itemView.findViewById(R.id.tvCategoryEmoji)
        val tvName: TextView = itemView.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.tvEmoji.text = category.emoji
        holder.tvName.text = category.name

        if (position == selectedPosition) {
            holder.cardCategory.setCardBackgroundColor(context.resources.getColor(R.color.primary, null))
            holder.tvName.setTextColor(context.resources.getColor(R.color.text_white, null))
        } else {
            holder.cardCategory.setCardBackgroundColor(context.resources.getColor(R.color.bg_white, null))
            holder.tvName.setTextColor(context.resources.getColor(R.color.text_secondary, null))
        }

        holder.cardCategory.setOnClickListener {
            val old = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(old)
            notifyItemChanged(selectedPosition)
            onCategoryClick(selectedPosition)
        }
    }

    override fun getItemCount() = categories.size
}