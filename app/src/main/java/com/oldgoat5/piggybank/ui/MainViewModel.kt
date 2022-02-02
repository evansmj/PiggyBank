package com.oldgoat5.piggybank.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.oldgoat5.piggybank.api.ContractInteractor
import com.oldgoat5.piggybank.common.PiggyBankViewModel
import com.oldgoat5.piggybank.contract.Abi
import com.walletconnect.walletconnectv2.client.WalletConnectClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
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

}
