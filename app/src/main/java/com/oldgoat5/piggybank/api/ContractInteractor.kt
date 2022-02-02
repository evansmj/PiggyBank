package com.oldgoat5.piggybank.api

import javax.inject.Inject

class ContractInteractor @Inject constructor(private val contractService: ContractService) {

    suspend fun connectToBlockchain() {
        contractService.connectToBlockchain()
    }

    suspend fun about(): String {
        return contractService.about()
    }
}
