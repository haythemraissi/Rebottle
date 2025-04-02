package com.example.rebottle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rebottle.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        auth = Firebase.auth

        setupViews(view)
        return view
    }

    private fun setupViews(view: View) {
        val welcomeTextView = view.findViewById<TextView>(R.id.welcomeTextView)
        val scanButton = view.findViewById<Button>(R.id.scanButton)
        val historyButton = view.findViewById<Button>(R.id.historyButton)

        val user = auth.currentUser
        welcomeTextView.text = getString(R.string.welcome_message, user?.email ?: "")

        scanButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_qrScanner)
        }

        historyButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_history)
        }
    }
}