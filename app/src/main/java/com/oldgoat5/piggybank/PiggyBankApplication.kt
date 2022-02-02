package com.oldgoat5.piggybank

import android.app.Application
import com.walletconnect.walletconnectv2.client.WalletConnect
import com.walletconnect.walletconnectv2.client.WalletConnectClient
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PiggyBankApplication : Application() {

    companion object {
        lateinit var instance: PiggyBankApplication
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initializeWalletConnect()
    }

    private fun initializeWalletConnect() {
        val appMetaData =
            WalletConnect.Model.AppMetaData(
                name = "Wallet Name",
                description = "Wallet Description",
                url = "Wallet Url",
                icons = listOf("")
            )

        val init = WalletConnect.Params.Init(
            application = this,
            isController = true,
            serverUrlConfig = WalletConnect.Params.ServerUrlConfig.ServerUrl("wss://relay.walletconnect.org/?projectId=cd00ebb24ed496a8e5d5cffcf2921025/"),
            metadata = appMetaData
        )

        WalletConnectClient.initialize(init)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        WalletConnectClient.shutdown()
    }
}
