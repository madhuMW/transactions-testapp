package nz.co.test.transactions.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import nz.co.test.transactions.data.mapper.IMapper
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.data.services.response.TransactionDTO
import nz.co.test.transactions.framework.Mapper

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataModule {
    @Provides
    @ActivityRetainedScoped
    fun provideMapper(): IMapper<List<TransactionDTO>, List<Transaction>> {
        return Mapper()
    }
}