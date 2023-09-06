package com.example.baseapp.base.view

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.baseapp.base.util.CommonUtil

abstract class
BaseActivity<B : ViewBinding, V : ViewModel> : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    private var mProgressDialog: ProgressDialog? = null

    lateinit var viewModel: V

    protected open var binding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding?.root)
        viewModelFactory = BaseViewModelFactory()
        viewModel = obtainViewModel()
    }

    abstract fun getViewBinding(): B

    abstract fun obtainViewModel(): V

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }



    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    fun EditText.textString(): String {
        return this.text.toString()
    }
    open fun hideLoading() {
        mProgressDialog?.let { dialog ->
            if (dialog.isShowing) {
                dialog.cancel()
            }
        }
    }
    open fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtil.showLoadingDialog(this)
    }

}

