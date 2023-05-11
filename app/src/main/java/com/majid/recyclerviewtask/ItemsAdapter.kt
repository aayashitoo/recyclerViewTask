package com.majid.recyclerviewtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.majid.recyclerviewtask.databinding.ItemsBinding

class ItemsAdapter(var list: ArrayList<String>,var clickInterface: ClickInterface) : RecyclerView.Adapter<ItemsAdapter.viewHolder>() {
    class viewHolder(var binding: ItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.viewHolder {
        val view = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.viewHolder, position: Int) {
        System.out.println("in adapter :$list")
        holder.binding.tvname.setText(list[position])
        holder.itemView.setOnClickListener{
            clickInterface.newClick(position)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

}