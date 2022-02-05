package com.oldgoat5.piggybank.api

import com.oldgoat5.piggybank.contract.Abi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.await
import kotlinx.coroutines.withContext
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import java.math.BigInteger
import java.util.concurrent.CompletableFuture
import javax.inject.Inject

class ContractService @Inject constructor() {

    private lateinit var piggyBankContract: Abi

    private var connected: CompletableFuture<Boolean> = CompletableFuture()
    private lateinit var web3j: Web3j

    suspend fun connectToBlockchain() {
        this.web3j = Web3j.build(
            HttpService("https://rpc-mumbai.maticvigil.com/v1/5009bc6ba430089a012a96d301f7287ae153f36f")
        )

        try {
            val clientVersion: Web3ClientVersion = web3j.web3ClientVersion().sendAsync().await()
            if (!clientVersion.hasError()) {
                //Toast.makeText(context, "connected to polygon-mumbai testnet", Toast.LENGTH_SHORT).show()
            } else {
                //Toast.makeText( ontext, clientVersion.error.message, Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            //Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

        val wallet = Credentials.create(
            "66f158b39024993edd076a098e5b8c54662a7e4aab531d2201b4be0fd8a9dce5",
            "0xe70d5BC7bB9d6eD4110ea113eDBDcFC022731e35"
        )

        this.piggyBankContract = Abi.load(
            "0x5e018fa786082ec2f13c4f6837cf9ce656070b6f",
            web3j,
            wallet,
            BigInteger("33333"),
            BigInteger("2000000000")
        )

        if (!this.piggyBankContract.isValid) {
            throw RuntimeException("invalid contract")
        }

        connected.complete(true)
    }

    suspend fun about(): String {
        return withContext(Dispatchers.IO) {
            connected.await()
            piggyBankContract.about().send()
        }
    }

    suspend fun getBankBalance(): BigInteger {
        return withContext(Dispatchers.IO) {
            connected.await()
            piggyBankContract.bankBalance().send()
        }
    }

    suspend fun deposit(wei: Int): Boolean {
        return withContext(Dispatchers.IO) {
            connected.await()
            piggyBankContract.deposit().send().isStatusOK
        }
    }

    suspend fun getMyBalance(): BigInteger {
        return withContext(Dispatchers.IO) {
            connected.await()
            web3j.ethGetBalance("0xe70d5BC7bB9d6eD4110ea113eDBDcFC022731e35", DefaultBlockParameter.valueOf("latest")).send().balance
        }
    }
}
