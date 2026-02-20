package com.example.feature_auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core.di.navigation.AppNavigator
import com.example.feature_auth.databinding.FragmentAuthBinding
import kotlinx.coroutines.launch

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding
        get() = _binding ?: throw RuntimeException("FragmentAuthBinding is null")

    private val component by lazy {
        DaggerAuthComponent.factory().create(
            (requireActivity().application as AuthDependenciesProvider).provideAuthDependencies()
        )
    }

    private val viewModel: AuthViewModel by viewModels {
        component.viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.isLoginEnabled.collect { isEnabled ->
                        binding.btnEnter.isEnabled = isEnabled
                    }
                }

                launch {
                    viewModel.urlEvent.collect { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                }
            }
        }

        binding.etEmail.doAfterTextChanged {
            viewModel.onEmailInput(it.toString())
        }

        binding.etPassword.doAfterTextChanged {
            viewModel.onPasswordInput(it.toString())
        }

        binding.btnEnter.setOnClickListener {
            (requireActivity() as AppNavigator).openMain()
        }

        binding.vkBtn.setOnClickListener {
            viewModel.onVkClick()
        }

        binding.okBtn.setOnClickListener {
            viewModel.onOkClick()
        }
    }

    companion object {
        fun newInstance() = AuthFragment()
    }
}