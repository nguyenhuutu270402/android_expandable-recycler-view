package com.example.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import pokercc.android.expandablerecyclerview.ExpandableRecyclerView


private val selections = listOf(
    SingleSelection("Gender", listOf("Male", "Female")),
    SingleSelection("Work Experience", listOf("0~3", "3~5", "5~10", "over 10")),
    SingleSelection("Type of work", listOf("Html", "Android", "iOS", "Java", "Other"))
)
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: ExpandableRecyclerView
    private lateinit var selectionAdapter: SelectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        selectionAdapter = SelectionAdapter(selections)
        recyclerView.adapter = selectionAdapter
    }

}
