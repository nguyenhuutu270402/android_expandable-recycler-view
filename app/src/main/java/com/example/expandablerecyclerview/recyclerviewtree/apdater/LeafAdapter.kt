package com.example.expandablerecyclerview.recyclerviewtree.apdater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.LayoutLeafBinding
import com.example.expandablerecyclerview.recyclerviewtree.model.Leaf

class LeafAdapter() : RecyclerView.Adapter<LeafAdapter.ViewHolder>() {
    private val trees = mutableListOf<Leaf>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutLeafBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(trees[position])
    }

    override fun getItemCount() = trees.size

    fun setData(data: List<Leaf>) {
        trees.clear()
        trees.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: LayoutLeafBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: Leaf) {
            binding.textLeaf.text = item.name
        }
    }
}
