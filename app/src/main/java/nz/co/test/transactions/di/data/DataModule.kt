package nz.co.test.transactions.di.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import nz.co.test.transactions.data.mapper.IMapper
import nz.co.test.transactions.data.repository.ITransactionRepository
import nz.co.test.transactions.data.model.Transaction
import nz.co.test.transactions.data.services.TransactionsService
import nz.co.test.transactions.data.services.response.TransactionDTO
import nz.co.test.transactions.framework.Mapper
import nz.co.test.transactions.framework.TransactionRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataModule {
    @Provides
    @ActivityRetainedScoped
    fun provideMapper(): IMapper<List<TransactionDTO>, List<Transaction>> {
        return Mapper()
    }

    @Provides
    @ActivityRetainedScoped
    fun provideRepository(
        transactionsService: TransactionsService,
        mapper: IMapper<List<TransactionDTO>, List<Transaction>>,
        @ApplicationContext context: Context
    ): ITransactionRepository {
        return TransactionRepository(transactionsService, mapper, context)
    }
}