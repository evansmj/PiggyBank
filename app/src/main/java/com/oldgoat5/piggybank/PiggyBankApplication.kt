package com.oldgoat5.piggybank

import android.app.Application
import com.walletconnect.walletconnectv2.client.WalletConnect
import com.walletconnect.walletconnectv2.client.WalletConnectClient

class PiggyBankApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appMetaData =
            WalletConnect.Model.AppMetaData(
                name = "Wallet Name",
                description = "Wallet Description",
                url = "Wallet Url",
                icons = listOf(""))


        val init = WalletConnect.Params.Init(
            application = this,
            isController = true,
            serverUrlConfig = WalletConnect.Params.ServerUrlConfig.ServerUrl("wss://relay.walletconnect.org/?projectId=cd00ebb24ed496a8e5d5cffcf2921025/"),
            metadata = appMetaData)

        WalletConnectClient.initialize(init)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        WalletConnectClient.shutdown()
    }
}
