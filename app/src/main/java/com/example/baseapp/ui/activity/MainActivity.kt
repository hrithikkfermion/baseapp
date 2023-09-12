package com.example.baseapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.example.baseapp.base.view.BaseActivity
import com.example.baseapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun obtainViewModel(): MainViewModel {
        return MainViewModel()
    }
}