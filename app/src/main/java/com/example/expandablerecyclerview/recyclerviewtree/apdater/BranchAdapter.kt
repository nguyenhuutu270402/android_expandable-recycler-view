package com.example.expandablerecyclerview.recyclerviewtree.apdater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.LayoutBranchBinding
import com.example.expandablerecyclerview.recyclerviewtree.model.Branch

class BranchAdapter(
    private val onItemClick: (Branch, Int) -> Unit
) : RecyclerView.Adapter<BranchAdapter.ViewHolder>() {
    private val trees = mutableListOf<Branch>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutBranchBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(trees[position])
    }

    override fun getItemCount() = trees.size

    fun setData(data: List<Branch>) {
        trees.clear()
        trees.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: LayoutBranchBinding,
        private val onBranchClick: (Branch, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = LeafAdapter()

        fun bindData(item: Branch) {
            binding.apply {
                textBranch.let {
                    it.text = item.name
                    it.setOnClickListener { onBranchClick(item, adapterPosition) }
                }
                recyclerViewLeaf.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
                recyclerViewLeaf.adapter = adapter
                adapter.setData(item.leafs)
            }
        }
    }
}
