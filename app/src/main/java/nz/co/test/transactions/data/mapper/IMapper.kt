package nz.co.test.transactions.data.mapper

interface IMapper<IN, OUT> {
    fun createTransactionList(modelDTO: IN): OUT
}