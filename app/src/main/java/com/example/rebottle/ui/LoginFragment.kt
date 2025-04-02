package com.example.rebottle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rebottle.R
import com.example.rebottle.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (email.isEmpty()) {
                binding.emailEditText.error = getString(R.string.email_required)
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.passwordEditText.error = getString(R.string.password_required)
                return@setOnClickListener
            }

            loginUser(email, password)
        }

        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginUser(email: String, password: String) {
        binding.progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val authResult = auth.signInWithEmailAndPassword(email, password).await()

                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.GONE

                    authResult.user?.let {
                        // Navigation sécurisée
                        if (findNavController().currentDestination?.id == R.id.loginFragment) {
                            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                        }
                    } ?: run {
                        Toast.makeText(context, "Compte introuvable", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Erreur: ${e.localizedMessage}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}