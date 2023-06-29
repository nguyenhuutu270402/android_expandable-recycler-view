package com.example.expandablerecyclerview.recyclerviewtree.apdater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.LayoutTreeBinding
import com.example.expandablerecyclerview.recyclerviewtree.model.Branch
import com.example.expandablerecyclerview.recyclerviewtree.model.Tree

class TreeAdapter(
    private val onItemClick: (Tree, Int) -> Unit
) : RecyclerView.Adapter<TreeAdapter.ViewHolder>() {
    private val trees = mutableListOf<Tree>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutTreeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(trees[position])
    }

    override fun getItemCount() = trees.size

    fun setData(data: List<Tree>) {
        trees.clear()
        trees.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: LayoutTreeBinding,
        private val onTreeClick: (Tree, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = BranchAdapter(this::onBranchClick)

        fun bindData(item: Tree) {
            binding.apply {
                textTree.text = item.name
                textTree.setOnClickListener { onTreeClick(item, adapterPosition) }
                recyclerViewBranch.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
                recyclerViewBranch.adapter = adapter
                adapter.setData(item.branches)
            }
        }

        private fun onBranchClick(branch: Branch, position: Int) {
            branch.isExpanded = !branch.isExpanded
            adapter.notifyItemChanged(position)
        }
    }
}
