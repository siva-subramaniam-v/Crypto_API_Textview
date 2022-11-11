package com.example.cryptoapitextview.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapitextview.network.CryptoDetailsApi
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    private val _cryptoList = MutableLiveData<String>()

    val cryptoList: LiveData<String>
        get() = _cryptoList

    init {
        getCryptoDetails()
    }

    private fun getCryptoDetails() {
        viewModelScope.launch {
            val listResult = CryptoDetailsApi.retrofitService.getCryptoDetailsAsync()
            try {
                if (listResult.isNotEmpty()) {
                    _cryptoList.value = listResult
                }
            } catch (t: Throwable) {
                _status.value = "Failure: ${t.message}"
            }
        }
    }
}