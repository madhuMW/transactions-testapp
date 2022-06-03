package nz.co.test.transactions.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.data.model.Transaction
import nz.co.test.transactions.databinding.ItemTransactionBinding

class TransactionsListAdapter(
    private val adapterListener: TransactionsListAdapterListener,
    private val context: Context
) :
    ListAdapter<Transaction, TransactionsListAdapter.ViewHolder>(
        DiffCallback
    ) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.apply {
            holder.bind(data!!)
        }
    }

    inner class ViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        adapterListener.onItemTap(item)
                    }
                }
            }
        }

        fun bind(transaction: Transaction) {
            binding.apply {
                id.text = transaction.id.toString()
                transactionDate.text = transaction.transactionDate.toString()
                summary.text=transaction.summary
            }
        }
    }

    interface TransactionsListAdapterListener {
        fun onItemTap(transaction: Transaction)
    }
}
