package com.oldgoat5.piggybank.ui

import androidx.lifecycle.viewModelScope
import com.oldgoat5.piggybank.api.ContractInteractor
import com.oldgoat5.piggybank.common.PiggyBankViewModel
import com.walletconnect.walletconnectv2.client.WalletConnectClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contractInteractor: ContractInteractor
) : PiggyBankViewModel() {

    override fun onCreate() {
        super.onCreate()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                contractInteractor.connectToBlockchain()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        WalletConnectClient.shutdown()
    }

    suspend fun getAboutText(): String {
        return contractInteractor.about()
    }

    suspend fun getBankBalance(): BigInteger {
        return contractInteractor.getBankBalance()
    }

    suspend fun deposit(wei: Int): Boolean {
        return contractInteractor.deposit(wei)
    }

    suspend fun getMyBalance(): BigInteger {
        return contractInteractor.getMyBalance()
    }

}
