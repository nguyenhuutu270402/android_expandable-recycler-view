package com.example.expandablerecyclerview.recyclerviewtree.model

data class Tree(
    val name: String,
    val branches: List<Branch>,
    var isExpanded: Boolean = false
)
