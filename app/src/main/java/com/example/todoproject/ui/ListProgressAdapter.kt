package com.example.todoproject.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.viewmodel.ListViewModel
import com.example.todoproject.R
import com.example.todoproject.model.Item

class ListProgressAdapter internal constructor(context: Context?,
                                               progressBar: ProgressBar,
                                               viewModel: ListViewModel
)
    : RecyclerView.Adapter<ListProgressAdapter.TaskViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<Item>()
    private val progressBar = progressBar
    private val context = context
    private val viewModel = viewModel

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.title)
        val itemDescription: TextView = itemView.findViewById(R.id.description)
        val item: View = itemView.findViewById(R.id.itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = list[position]
        holder.itemTitle.text = item.title
        if (item.description == "") {
            holder.itemDescription.text = "There is no description for this task"
        } else {
            holder.itemDescription.text = item.description
        }

        if (item.done == true) {
            holder.item.setBackgroundColor(Color.parseColor("#7CFC00"))
        } else {
            holder.item.setBackgroundColor(Color.parseColor("#ffffff"))
        }

        progressBar.visibility = View.GONE

        holder.item.setOnClickListener {
            item.done = true
            holder.item.setBackgroundColor(Color.parseColor("#00aaff"))
            viewModel.update(item)
        }

        holder.item.setOnLongClickListener {
            val menu = context?.let { context -> PopupMenu(context, it) }
            if (menu != null) {
                menu.inflate(R.menu.menu_main)
                menu.show()
                menu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete -> {
                            viewModel.delete(item)
                            true
                        }
                        else -> {
                            true
                        }
                    }
                }
            }
            true
        }
    }

    internal fun setList(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size


}