package com.oldgoat5.piggybank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.walletconnect.walletconnectv2.client.WalletConnect
import com.walletconnect.walletconnectv2.client.WalletConnectClient
import org.web3j.crypto.ContractUtils
import org.web3j.crypto.Credentials
import org.web3j.ens.Contracts
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import org.web3j.protocol.http.HttpService
import org.web3j.tx.Contract
import java.math.BigInteger

class MainActivity : AppCompatActivity(), WalletConnectClient.DappDelegate {

    private lateinit var titleTextView: TextView

    init {
        WalletConnectClient.setDappDelegate(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextView = findViewById(R.id.title_text_view)

        connectToBlockchain()
    }

    override fun onDestroy() {
        super.onDestroy()

        WalletConnectClient.shutdown()
    }

    private fun connectToBlockchain() {
        val web3j: Web3j = Web3j.build(
            HttpService("https://rpc-mumbai.maticvigil.com/v1/5009bc6ba430089a012a96d301f7287ae153f36f")
        )

        try {
            val clientVersion: Web3ClientVersion = web3j.web3ClientVersion().sendAsync().get()
            if (!clientVersion.hasError()) {
                Toast.makeText(this, "connected to polygon-mumbai testnet", LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, clientVersion.error.message, LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message, LENGTH_LONG).show()
        }

        val wallet = Credentials.create("66f158b39024993edd076a098e5b8c54662a7e4aab531d2201b4be0fd8a9dce5", "0xe70d5BC7bB9d6eD4110ea113eDBDcFC022731e35")
        val piggyBankContract = Abi.load(
            "0x5e018fa786082ec2f13c4f6837cf9ce656070b6f",
            web3j,
            wallet,
            BigInteger("33333"),
            BigInteger("2000000000"))

        var response = ""
        val t = piggyBankContract.about().sendAsync().get()

        Toast.makeText(this, "piggy bank: " + t, LENGTH_SHORT).show()

        titleTextView.text = t
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
