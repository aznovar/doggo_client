package com.ru.appdoggo.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ru.appdoggo.R
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract val layoutId: Int

    open val titleToolbar = R.string.app_name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onResume() {
        super.onResume()
    }

    inline fun base(block: BaseActivity.() -> Unit) {
        activity.base(block)
    }

    open fun onBackPressed() {}

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }

    fun close() = fragmentManager?.popBackStack()


    fun hideSoftKeyboard() = base { hideSoftKeyboard() }

}