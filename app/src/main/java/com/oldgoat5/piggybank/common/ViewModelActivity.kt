package com.oldgoat5.piggybank.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class ViewModelActivity<T: PiggyBankViewModel> : AppCompatActivity() {

    protected abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
