package com.example.feature_course.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core.di.extensions.dp
import com.example.feature_course.CourseListUiState
import com.example.feature_course.R
import com.example.feature_course.databinding.FragmentFavouriteCourseBinding
import com.example.feature_course.databinding.FragmentMainScreenBinding
import com.example.feature_course.di.CourseDependenciesProvider
import com.example.feature_course.di.DaggerCourseComponent
import com.example.feature_course.list.CoursesAdapter
import com.example.feature_course.list.VerticalSpaceItemDecoration
import kotlinx.coroutines.launch

class FavouriteCourseFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteCourseBinding

    private val component by lazy {
        DaggerCourseComponent.factory().create(
            (requireActivity().application as CourseDependenciesProvider).provideCourseDependencies()
        )
    }

    private val viewModel: FavouriteCourseViewModel by viewModels {
        component.viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteCourseBinding.inflate(layoutInflater)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.left, systemBars.top, v.right, v.bottom)
            insets
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CoursesAdapter(::onFavouriteClick)

        binding.rvFavouriteCoursesList.adapter = adapter
        binding.rvFavouriteCoursesList.addItemDecoration(VerticalSpaceItemDecoration(16.dp))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect { state ->
                        when(state){
                            CourseListUiState.Initial,
                            CourseListUiState.Loading -> {
                                // Здесь показывает прогресс бар
                            }
                            CourseListUiState.Error -> {
                                // показываем что то в случае ошибки
                            }
                            is CourseListUiState.Success -> {
                                adapter.submitList(state.courses)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onFavouriteClick(courseId: Int) {
        viewModel.toggleFavourite(courseId)
    }

    companion object {
        fun newInstance() =
            FavouriteCourseFragment()
    }
}