package com.example.expandablerecyclerview.recyclerviewtree.model

data class Branch(
    val name: String,
    val leafs: List<Leaf>,
    var isExpanded: Boolean = false
)
