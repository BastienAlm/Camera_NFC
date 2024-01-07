
package com.gotham.cashmanager.ui.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gotham.cashmanager.databinding.FragmentScanBinding

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import com.gotham.cashmanager.MainActivity
import com.gotham.cashmanager.R
import com.gotham.cashmanager.ui.login.LoginFragment


class ScanFragment : Fragment() {

        private var _binding: FragmentScanBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val homeViewModel =
                ViewModelProvider(this).get(ScanViewModel::class.java)

           // requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, ScanFragment()).commit()



            _binding = FragmentScanBinding.inflate(inflater, container, false)
            val root: View = binding.root
            //val textView: TextView = binding.textScan
           /* homeViewModel.text.observe(viewLifecycleOwner) {
                textView.text = it
            }*/


            return root
        }



        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

}