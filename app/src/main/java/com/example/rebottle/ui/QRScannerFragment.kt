package com.example.rebottle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rebottle.R
import com.example.rebottle.databinding.FragmentQrScannerBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class QRScannerFragment : Fragment() {
    private var _binding: FragmentQrScannerBinding? = null
    private val binding get() = _binding!!

    // Configure le lancement du scanner
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents != null) {
            // Traitement du résultat
            processScanResult(result.contents)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQrScannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScanner()
    }

    private fun setupScanner() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt(getString(R.string.scan_prompt))
            setCameraId(0)  // Caméra arrière
            setBeepEnabled(true)
            setOrientationLocked(true)
        }

        // Lance le scanner au premier affichage
        barcodeLauncher.launch(options)
    }

    private fun processScanResult(content: String) {
        // TODO: Implémentez votre logique de traitement ici
        // Exemple : navigation vers un écran de confirmation
        binding.scanResultText.text = getString(R.string.scan_result, content)
        binding.scanResultText.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}