import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rebottle.model.HistoryItem
import com.example.rebottle.databinding.ItemHistoryBinding


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class HistoryAdapter : ListAdapter<HistoryItem, HistoryAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            itemType.text = item.type
            itemPrice.text = "%.2f €".format(item.price)
            itemDate.text = item.date
            executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HistoryItem>() {
        override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem.id == newItem.id // Utilisez un identifiant unique
        }

        override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean {
            return oldItem == newItem
        }
    }
}