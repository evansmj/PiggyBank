package com.oldgoat5.piggybank.common

import androidx.lifecycle.ViewModel

abstract class PiggyBankViewModel : ViewModel() {

    open fun onCreate() {
    }

    open fun onResume() {
    }

    open fun onPause() {
    }

    open fun onDestroy() {
    }
}
