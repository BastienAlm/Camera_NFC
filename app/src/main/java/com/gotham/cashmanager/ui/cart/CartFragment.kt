package com.gotham.cashmanager.ui.cart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gotham.cashmanager.R
import com.gotham.cashmanager.databinding.ActivityCartBinding
import com.gotham.cashmanager.ui.login.LoginFragment


class  CartFragment : Fragment(), CartItemsAdapter.DataPassListener, LoginFragment.DataPassListener2{

    private var _binding: ActivityCartBinding? = null
    private val binding get() = _binding!!
     var payementDialog = PayementDialog();
    var login = LoginFragment()
    var access_token:String = ""

    val sharedPref = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)



    companion object {
        // This is the companion object where you can define properties or functions
        const val TAG = "CartFragment"
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityCartBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.btnPayment.setOnClickListener { root -> if(access_token == ""){ findNavController().navigate(R.id.navigation_login)}else{findNavController().navigate(R.id.navigation_paymentDialog)}  } /*requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment()).addToBackStack(null).commit()}
        else requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, payementDialog).addToBackStack(null).commit()}*/

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        //val cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)

       // bindingCart = ItemCartBinding.inflate(layoutInflater)

        val item = CartItems("Œufs", "6.30", 2)
        val item1 = CartItems("Nutella", "7.10", 0)
        val item2 = CartItems("Créme", "2.99", 1)
        val item3 = CartItems("Jus", "3.69", 3)
        val item4 = CartItems("Legume", "4.50", 4)
        val item5 = CartItems("Riz", "2.90", 5)
        val item6 = CartItems("Yaourt", "1.00", 6)


        val cartAdapter = CartItemsAdapter(arrayListOf(item, item1,item2,item3,item4,item5,item6))
        cartAdapter.setDataPassListener(this)
        login.setDataPassListener2(this)
        val recyclerView: RecyclerView = binding.rvcart
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cartAdapter

      // binding.totalht.text = String.format("%.2f", cartViewModel.sum.value );

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val access_token = sharedPref.getString("access_token", "")



        }

    override fun onDataPassed2(data: String) {
        println(data)
        //access_token = data

    }


    override fun onDataPassed(data: String) {
        binding.totalht.text = data;
        binding.taxe.text = taxe(data)
        binding.totalttc.text = ttc(binding.taxe, data)

    }
     fun taxe(data: String) : String {
         var t: Double = data.toDouble()
         var x: Double = t * 20/100
         return String.format("%.2f", x)

     }
     fun ttc(taxe: TextView, data: String) : String {
         var d: Double = data.toDouble()
         var t: Double = taxe.text.toString().toDouble() + d

         return String.format("%.2f", t)

     }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}