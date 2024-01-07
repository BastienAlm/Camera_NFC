package com.gotham.cashmanager.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.gotham.cashmanager.R
import com.gotham.cashmanager.databinding.FragmentPayementDialogBinding
import com.gotham.cashmanager.ui.scan.ScanFragment


class PayementDialog : Fragment() {
    private var _binding: FragmentPayementDialogBinding? = null
    // lateinit var bindingCart: ItemCartBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPayementDialogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cheque.setOnClickListener { root -> requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, ScanFragment()).commit()
             //getFragmentManager()?.popBackStack()
           // requireActivity().supportFragmentManager.beginTransaction().(R.id.container, CartFragment()).commit()

        }
        binding.carte.setOnClickListener { root -> requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, CartFragment()).commit() }//findNavController().navigate() }
        //val navController = findNavController(R.id.nav_host_fragment_activity_main)


        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

    }

 /*   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // _binding = FragmentPayementDialogBinding.inflate(inflater, container, false)
       // val root: View = binding.root

        //binding.annuler.setOnClickListener { root ->  requireActivity().supportFragmentManager.popBackStackImmediate() }
        return inflater.inflate(R.layout.fragment_payement_dialog, container, false)

    }*/

    companion object {

            }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}