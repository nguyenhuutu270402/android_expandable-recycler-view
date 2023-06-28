package com.example.expandablerecyclerview

data class SingleSelection<T>(
    val name: String,
    val options: List<T>,
    var selectedIndex: Int = -1
) : Selection

internal interface Selection