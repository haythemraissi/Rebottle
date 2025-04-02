package com.example.rebottle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rebottle.R
import com.example.rebottle.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if (validateInputs(name, email, password, confirmPassword)) {
                registerUser(name, email, password)
            }
        }

        binding.loginTextView.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun validateInputs(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            binding.nameEditText.error = getString(R.string.name_required)
            isValid = false
        }

        if (email.isEmpty()) {
            binding.emailEditText.error = getString(R.string.email_required)
            isValid = false
        }

        if (password.isEmpty()) {
            binding.passwordEditText.error = getString(R.string.password_required)
            isValid = false
        } else if (password.length < 6) {
            binding.passwordEditText.error = getString(R.string.password_length_error)
            isValid = false
        }

        if (confirmPassword != password) {
            binding.confirmPasswordEditText.error = getString(R.string.passwords_not_match)
            isValid = false
        }

        return isValid
    }

    private fun registerUser(name: String, email: String, password: String) {
        showLoading(true)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                showLoading(false)

                if (task.isSuccessful) {
                    // Enregistrer le nom dans Firestore (exemple optionnel)
                    saveAdditionalUserData(name, email)
                    navigateToDashboard()
                } else {
                    showError(task.exception?.message ?: getString(R.string.registration_failed))
                }
            }
    }

    private fun saveAdditionalUserData(name: String, email: String) {
        // Optionnel : Enregistrer des données supplémentaires dans Firestore
        // Voir la section suivante pour l'implémentation
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.registerButton.isEnabled = !show
    }

    private fun showError(message: String) {
        binding.emailEditText.error = message
    }

    private fun navigateToDashboard() {
        findNavController().navigate(R.id.action_registerFragment_to_dashboardFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}