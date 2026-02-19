package com.example.feature_course.list.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_course.list.CourseDiffCallback
import com.example.feature_course.list.courseAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class CoursesAdapter(
    onLikeClick: (courseId: Int) -> Unit
): ListAdapter<CourseUi, RecyclerView.ViewHolder>(CourseDiffCallback()) {

    private val delegatesManager = AdapterDelegatesManager<List<CourseUi>>()
        .addDelegate(courseAdapterDelegate(onLikeClick))

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(currentList, position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        delegatesManager.onBindViewHolder(currentList, position, holder)
    }
}