package com.example.feature_course.list

import androidx.recyclerview.widget.DiffUtil
import com.example.feature_course.list.main.CourseUi

class CourseDiffCallback : DiffUtil.ItemCallback<CourseUi>() {
    override fun areItemsTheSame(
        oldItem: CourseUi,
        newItem: CourseUi
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CourseUi,
        newItem: CourseUi
    ): Boolean {
        return oldItem == newItem
    }
}