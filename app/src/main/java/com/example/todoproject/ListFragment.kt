package com.example.todoproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.todoproject.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null

    private var viewModel: ListViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = ListViewModelFactory(application)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)

        binding?.lifecycleOwner = this

        val recyclerView = binding?.recyclerView
        val adapter = ListProgressAdapter(context, binding?.progressbar!!, viewModel!!)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val itemDecoration: ItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView?.addItemDecoration(itemDecoration)

        viewModel?.list?.observe(viewLifecycleOwner, Observer { list ->
            list?.let { adapter.setList(it)
                if(adapter.itemCount == 0){
                    progressbar.visibility = View.GONE
                    recyclerView?.visibility = View.GONE
                    binding?.empty?.visibility = View.VISIBLE
                } else {
                    recyclerView?.visibility = View.VISIBLE
                    binding?.empty?.visibility = View.GONE
                }}
        })

        binding?.listViewModel = viewModel

        binding?.fab?.setOnClickListener {
                view: View ->
            view.findNavController().navigate(R.id.action_listFragment_to_itemFragment)
        }

        return binding?.root
    }

}