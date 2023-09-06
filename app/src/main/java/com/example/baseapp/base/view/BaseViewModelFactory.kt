package com.example.baseapp.base.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModelFactory is used to create ViewModels that have constructor with parameters. It's used by dagger.
 */
internal class BaseViewModelFactory :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.newInstance()
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }
}