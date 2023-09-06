package com.example.baseapp.base.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baseapp.base.network.ApiRepository



open class BaseViewModel() : ViewModel() {

    var apiRepository = ApiRepository()
    val showProgress: MutableLiveData<ProgressState> = MutableLiveData()
    val showError: MutableLiveData<Throwable> = MutableLiveData()
}
