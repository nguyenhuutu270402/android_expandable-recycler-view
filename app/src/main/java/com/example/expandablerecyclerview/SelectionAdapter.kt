package com.example.expandablerecyclerview

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import pokercc.android.expandablerecyclerview.ExpandableAdapter
import pokercc.android.expandablerecyclerview.ExpandableAdapter.ViewHolder

class SelectionGroupVH(itemView: View) : ViewHolder(itemView) {
    val titleText: TextView = itemView.findViewById(R.id.title_text)
    val arrowImage: View = itemView.findViewById(R.id.arrow_image)
}

class SelectionChildVH(itemView: View) : ViewHolder(itemView) {
    val radioButton: RadioButton = itemView.findViewById(R.id.radio_button)
}

class SelectionAdapter(private val selections: List<SingleSelection<String>>) :
    ExpandableAdapter<ViewHolder>() {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.selection_parent_item, parent, false)
        return SelectionGroupVH(itemView)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.selection_child_item, parent, false)
        return SelectionChildVH(itemView)
    }

    override fun onBindChildViewHolder(
        holder: ViewHolder,
        groupPosition: Int,
        childPosition: Int,
        payloads: List<Any>
    ) {
        val childHolder = holder as SelectionChildVH
        val selection = selections[groupPosition]
        val option = selection.options[childPosition]
        childHolder.radioButton.isChecked = childPosition == selection.selectedIndex
        childHolder.radioButton.text = option
        childHolder.itemView.setOnClickListener {
            onSelectionChanged(selection, groupPosition, childPosition)
        }
    }

    private fun onSelectionChanged(
        selection: SingleSelection<String>,
        groupPosition: Int,
        childPosition: Int
    ) {
        val oldSelectedIndex = selection.selectedIndex
        if (oldSelectedIndex == childPosition) return
        selection.selectedIndex = childPosition
        notifyChildChange(groupPosition, childPosition)
        if (oldSelectedIndex >= 0) {
            notifyChildChange(groupPosition, oldSelectedIndex)
        }
    }

    override fun onBindGroupViewHolder(
        holder: ViewHolder,
        groupPosition: Int,
        expand: Boolean,
        payloads: List<Any>
    ) {
        val groupHolder = holder as SelectionGroupVH
        val item = selections[groupPosition]
        groupHolder.titleText.text = "${groupPosition + 1}.${item.name}"
        groupHolder.arrowImage.rotation = if (expand) 0f else -90.0f
    }

    override fun onGroupViewHolderExpandChange(
        holder: ViewHolder,
        groupPosition: Int,
        animDuration: Long,
        expand: Boolean
    ) {
        val groupHolder = holder as SelectionGroupVH
        val arrowImage = groupHolder.arrowImage
        if (expand) {
            ObjectAnimator.ofFloat(arrowImage, View.ROTATION, 0f)
                .setDuration(animDuration)
                .start()
        } else {
            ObjectAnimator.ofFloat(arrowImage, View.ROTATION, -90f)
                .setDuration(animDuration)
                .start()
        }
    }

    override fun getGroupCount(): Int {
        return selections.size
    }

    override fun getChildCount(groupPosition: Int): Int {
        return selections[groupPosition].options.size
    }
}
