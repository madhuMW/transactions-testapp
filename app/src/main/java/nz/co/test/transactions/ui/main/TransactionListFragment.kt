package nz.co.test.transactions.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.R
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.databinding.FragmentTransactionListBinding
import nz.co.test.transactions.ui.adapter.TransactionsListAdapter

class TransactionListFragment : Fragment(R.layout.fragment_transaction_list),
    TransactionsListAdapter.TransactionsListAdapterListener {
    private var _binding: FragmentTransactionListBinding? = null
    private val binding get() = _binding!!
    private lateinit var transactionsListAdapter: TransactionsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionListBinding.inflate(inflater, container, false)
        transactionsListAdapter = TransactionsListAdapter(this, requireActivity())

        binding.apply {
            transactionsRecycler.layoutManager = LinearLayoutManager(requireActivity())
            transactionsRecycler.adapter = transactionsListAdapter
            transactionsRecycler.adapter?.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemTap(transaction: Transaction) {
        val action = TransactionListFragmentDirections.actionTransDetail(transaction)
        findNavController().navigate(action)
    }
}