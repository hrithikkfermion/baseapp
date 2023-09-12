package com.example.baseapp.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.baseapp.base.view.BaseViewModel
import com.example.baseapp.model.User
import retrofit2.Response


class MainViewModel : BaseViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    val singleUser: LiveData<Response<User>> = liveData {
        isLoading.value = true
        try {
            val response = apiRepository.getApiService().getSingleUser()
            println("SingleUser is :::::: ${response.body()}")
            if (response.isSuccessful && response.code() == 200) {
                emit(response)
                isLoading.value = false
            } else {
                isLoading.value = false

            }

        } catch (e: Exception) {
            isLoading.value = false
            println("Exception ::::::::::::::  ${e.localizedMessage}")
        }
    }

}