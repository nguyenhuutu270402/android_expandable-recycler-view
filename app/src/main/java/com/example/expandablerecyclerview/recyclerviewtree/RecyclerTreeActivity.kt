package com.example.expandablerecyclerview.recyclerviewtree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.databinding.ActivityRecyclerTreeBinding
import com.example.expandablerecyclerview.recyclerviewtree.apdater.TreeAdapter
import com.example.expandablerecyclerview.recyclerviewtree.model.Branch
import com.example.expandablerecyclerview.recyclerviewtree.model.Leaf
import com.example.expandablerecyclerview.recyclerviewtree.model.Tree

class RecyclerTreeActivity : AppCompatActivity() {
    private val binding: ActivityRecyclerTreeBinding by lazy {
        ActivityRecyclerTreeBinding.inflate(
            layoutInflater
        )
    }
    private val adapter = TreeAdapter(this::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupData()
    }

    private fun setupData() {
        binding.recyclerViewTree.adapter = adapter
        adapter.setData(getTrees())
    }

    private fun getTrees(): List<Tree> {
        val trees = mutableListOf<Tree>()
        for (i in 0..10) {
            val branches = mutableListOf<Branch>()
            for (j in 0..5) {
                val leafs = mutableListOf<Leaf>()
                for (k in 0..5) {
                    leafs.add(Leaf("Leaf $k"))
                }
                branches.add(Branch("Branch $j", leafs))
            }

            trees.add(Tree("Tree $i", branches))
        }
        return trees
    }

    private fun onItemClick(tree: Tree, position: Int) {
        tree.isExpanded = !tree.isExpanded
        adapter.notifyItemChanged(position)

    }
}