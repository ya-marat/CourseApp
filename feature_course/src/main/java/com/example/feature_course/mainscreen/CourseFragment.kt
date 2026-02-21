package com.example.feature_course.mainscreen

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.extensions.dp
import com.example.feature_course.CourseListUiState
import com.example.feature_course.databinding.FragmentMainScreenBinding
import com.example.feature_course.di.CourseDependencies
import com.example.feature_course.di.CourseDependenciesProvider
import com.example.feature_course.di.DaggerCourseComponent
import com.example.feature_course.list.VerticalSpaceItemDecoration
import com.example.feature_course.list.CoursesAdapter
import kotlinx.coroutines.launch

class CourseFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding

    private val component by lazy {
        DaggerCourseComponent.factory().create(
            (requireActivity().application as CourseDependenciesProvider).provideCourseDependencies()
        )
    }

    private val viewModel: CourseViewModel by viewModels {
        component.viewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(layoutInflater)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, bars.top, 0, 0)
            insets
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CoursesAdapter(::onCourseLikeClick)

        binding.rvMainCoursesList.adapter = adapter
        binding.rvMainCoursesList.addItemDecoration(VerticalSpaceItemDecoration(16.dp))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.state.collect { state ->
                        when (state) {
                            CourseListUiState.Initial,
                            CourseListUiState.Loading -> {

                            }

                            is CourseListUiState.Success -> {
                                adapter.submitList(state.courses)
                            }

                            CourseListUiState.Error -> {
                                // Сообщить об ошибке при загрузке
                            }
                        }
                    }
                }
            }
        }

        binding.tvSort.setOnClickListener {
            viewModel.onSortClicked()
        }
    }

    private fun onCourseLikeClick(courseId: Int) {
        viewModel.toggleFavourite(courseId)
    }

    companion object {

        fun newInstance() =
            CourseFragment()
    }
}