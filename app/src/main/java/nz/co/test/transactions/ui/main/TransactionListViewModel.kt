package nz.co.test.transactions.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import nz.co.test.transactions.data.model.Transaction
import nz.co.test.transactions.interactors.GetTransactions
import nz.co.test.transactions.utils.ResponseResult
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val getTransactions: GetTransactions
) :
    ViewModel() {
    private val transactionsListMutableLiveData = MutableLiveData<List<Transaction>>()
    val transactionsListLiveData: LiveData<List<Transaction>> get() = transactionsListMutableLiveData
    private val errorMutableLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = errorMutableLiveData

    fun getTransactionList() {
        viewModelScope.launch(coroutineDispatcher) {
            when (val result = getTransactions()) {
                is ResponseResult.Success -> transactionsListMutableLiveData.postValue(result.data)
                is ResponseResult.Error -> errorMutableLiveData.postValue(result.exception.message)
            }
        }
    }
}