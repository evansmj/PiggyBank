package com.oldgoat5.piggybank.di

import android.content.Context
import com.oldgoat5.piggybank.PiggyBankApplication
import com.oldgoat5.piggybank.api.ContractInteractor
import com.oldgoat5.piggybank.api.ContractService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesContractService(): ContractService {
        return ContractService()
    }

    @Provides
    @Singleton
    fun providesContractInteractor(): ContractInteractor {
        return ContractInteractor(providesContractService())
    }

    @Provides
    @Reusable
    fun providesContext(): Context {
        return PiggyBankApplication.instance.applicationContext
    }
}
