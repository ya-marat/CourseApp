package com.example.feature_course.list

import com.example.feature_course.R
import com.example.feature_course.databinding.ItemCourseBinding
import com.example.feature_course.list.main.CourseUi
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseAdapterDelegate(
    onLikeClick: (courseId: Int) -> Unit
) = adapterDelegateViewBinding<CourseUi, CourseUi, ItemCourseBinding>(
    { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) }
) {
//    binding.tvDetailCourse.setOnClickListener {
//
//    }

    bind {
        binding.tvCourseName.text = item.title
        binding.tvCourseDescription.text = item.text
        binding.tvCoursePrice.text = item.price
        binding.ivCourseCover.setImageResource(R.drawable.course_cover_1)
    }
}
