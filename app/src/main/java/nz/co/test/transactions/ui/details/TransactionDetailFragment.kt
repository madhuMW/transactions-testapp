package nz.co.test.transactions.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import nz.co.test.transactions.R
import nz.co.test.transactions.data.model.Transaction
import nz.co.test.transactions.databinding.FragmentTransactionDetailBinding

@AndroidEntryPoint
class TransactionDetailFragment : Fragment(R.layout.fragment_transaction_detail) {
    private lateinit var transaction: Transaction
    private var _binding: FragmentTransactionDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<TransactionDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionDetailBinding.inflate(inflater, container, false)
        binding.apply {
            transaction = args.transaction
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.id.text = transaction.id.toString()
        binding.transactionDate.text = transaction.transactionDate.toString()
        binding.summary.text = transaction.summary
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}