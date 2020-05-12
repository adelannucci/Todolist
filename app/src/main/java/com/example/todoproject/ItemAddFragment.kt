package com.example.todoproject

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.todoproject.databinding.FragmentItemBinding
import kotlinx.android.synthetic.main.fragment_item.view.*


class ItemAddFragment : Fragment() {
    private var binding: FragmentItemBinding? = null

    private var viewModel: ItemAddViewModel? = null

    private var description: EditText? = null

    private var title: EditText? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_item, container, false
        )

        description = binding?.description
        title = binding?.title

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ItemAddViewModelFactory(application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ItemAddViewModel::class.java)

        binding?.lifecycleOwner = this

        binding?.add?.setOnClickListener { v: View ->
            if (!TextUtils.isEmpty(title?.text)) {
                binding?.itemAddViewModel = viewModel
                viewModel?.insert(
                    Item(
                        title = title?.text.toString(),
                        description = description?.text.toString()
                    )
                )
                hideKeyboard()
            }
            v.add.findNavController().navigate(R.id.action_itemFragment_to_listFragment)
        }

        return binding?.root
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}