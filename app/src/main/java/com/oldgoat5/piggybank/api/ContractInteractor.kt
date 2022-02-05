package com.oldgoat5.piggybank.api

import java.math.BigInteger
import javax.inject.Inject

class ContractInteractor @Inject constructor(private val contractService: ContractService) {

    suspend fun connectToBlockchain() {
        contractService.connectToBlockchain()
    }

    suspend fun about(): String {
        return contractService.about()
    }

    suspend fun getBankBalance(): BigInteger {
        return contractService.getBankBalance()
    }

    suspend fun deposit(wei: Int): Boolean {
        return contractService.deposit(wei)
    }

    suspend fun getMyBalance(): BigInteger {
        return contractService.getMyBalance()
    }
}
