package com.oldgoat5.piggybank.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.oldgoat5.piggybank.contract.Abi
import com.oldgoat5.piggybank.R
import com.oldgoat5.piggybank.common.ViewModelActivity
import com.walletconnect.walletconnectv2.client.WalletConnect
import com.walletconnect.walletconnectv2.client.WalletConnectClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

@AndroidEntryPoint
class MainActivity : ViewModelActivity<MainViewModel>(), WalletConnectClient.DappDelegate {

    override val viewModel: MainViewModel by viewModels()

    private lateinit var titleTextView: TextView

    init {
        WalletConnectClient.setDappDelegate(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextView = findViewById(R.id.title_text_view)

        lifecycleScope.launch {
            titleTextView.text = viewModel.getAboutText()
        }
    }

    /**
     * Pairing is a specialized sequence which has fixed permissions to only allow a client to
     * propose sessions through it using the method wc_sessionPropose which it will be used as a
     * signal out-of-band for session proposals.
     */
    override fun onPairingSettled(settledPairing: WalletConnect.Model.SettledPairing) {
        TODO("Not yet implemented")
    }

    /**
     * Session is a generalized sequence which has customizable permissions regarding JSON-RPC requests,
     * notifications emitted and also what accounts are exposed based on the set of chains determined
     * in the permissions.
     */
    override fun onSessionApproved(approvedSession: WalletConnect.Model.ApprovedSession) {
        TODO("Not yet implemented")
    }

    override fun onSessionRejected(rejectedSession: WalletConnect.Model.RejectedSession) {
        TODO("Not yet implemented")
    }
}
