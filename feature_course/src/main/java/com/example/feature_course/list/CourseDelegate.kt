package com.example.feature_course.list

import com.example.feature_course.R
import com.example.feature_course.databinding.ItemCourseBinding
import com.example.feature_course.CourseUi
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseAdapterDelegate(
    onLikeClick: (courseId: Int) -> Unit
) = adapterDelegateViewBinding<CourseUi, CourseUi, ItemCourseBinding>(
    { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) }
) {
    binding.ibFavouriteToggle.setOnClickListener {
        onLikeClick(item.id)
    }

    bind {
        binding.tvCourseName.text = item.title
        binding.tvCourseDescription.text = item.text
        binding.tvCoursePrice.text =
            context.getString(com.example.core.R.string.currency_symbol, item.price)

        binding.tvRating.text = item.rate
        binding.tvDate.text = item.publishDate
        binding.ivCourseCover.setImageResource(item.coverResId)

        binding.ibFavouriteToggle.setImageResource(
            if (item.isFavourite) R.drawable.in_favourite_ic else R.drawable.not_in_favourite_ic
        )
    }
}
