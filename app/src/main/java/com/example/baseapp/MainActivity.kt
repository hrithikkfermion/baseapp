package com.example.baseapp

import android.os.Bundle
import android.widget.Toast
import com.example.baseapp.base.view.BaseActivity
import com.example.baseapp.databinding.ActivityMainBinding
import com.example.baseapp.ui.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoading()
        viewModel.singleUser.observe(this@MainActivity) {
            if (it.isSuccessful && it.code() == 200) {
                hideLoading()
                binding?.textView?.text = it.body().toString()
            }else{
                hideLoading()
                Toast.makeText(this, "Some error", Toast.LENGTH_SHORT).show()
            }

        }
        viewModel.isLoading.observe(this@MainActivity){
            if (!it) hideLoading()
            else showLoading()
        }

    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun obtainViewModel(): MainViewModel {
        return MainViewModel()
    }
}