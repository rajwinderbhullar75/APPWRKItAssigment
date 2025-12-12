package com.rsb.appwrkitassigment.UI.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsb.appwrkitassigment.UI.activities.DetailActivity
import com.rsb.appwrkitassigment.UI.activities.MainActivity
import com.rsb.appwrkitassigment.databinding.ListItemBinding
import com.rsb.appwrkitassigment.model.ListItemModel

class MyAdapter(private val mainActivity: MainActivity, private val dataSet: List<ListItemModel>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ListItemModel) {
            binding.model = model
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)

//        if (item.status != "Completed") {
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ITEM_ID, item.id)
                mainActivity.getUpdatedItem.launch(intent)
            }
//        } else {
//            holder.itemView.setOnClickListener(null)
//        }
    }

    override fun getItemCount() = dataSet.size
}
