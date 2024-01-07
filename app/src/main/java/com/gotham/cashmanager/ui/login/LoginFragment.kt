package com.gotham.cashmanager.ui.login

import android.app.Notification.Action
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gotham.cashmanager.MainActivity
import com.gotham.cashmanager.R
import com.gotham.cashmanager.databinding.FragmentLoginBinding
import com.gotham.cashmanager.ui.cart.CartItemsAdapter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import com.gotham.cashmanager.ui.cart.PayementDialog
import com.gotham.cashmanager.ui.scan.ScanFragment


class LoginFragment : Fragment() {

   // private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val client = OkHttpClient()

    private val binding get() = _binding!!

    private var dataPassListener2: DataPassListener2? = null

    fun setDataPassListener2(listener: DataPassListener2) {
        this.dataPassListener2 = listener
    }

    fun passDataToParent2(data: String) {
        dataPassListener2?.onDataPassed2(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login


        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
               /* loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )*/
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                /*loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )*/
            }
            false
        }

        loginButton.setOnClickListener {
            login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    fun login(username: String, password: String) {

        val body: RequestBody = FormBody.Builder().add("email",username).add("password", password).build()

       // println(body)
        val request: Request = Request.Builder()
            .url("http://10.0.2.2:8080/auth/login")
            .post(body)
            .build()
       // println(request)

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) = println(e)
            override fun onResponse(call: Call, response: Response) {

                var jsonObject: JSONObject = JSONObject(response.body()?.string());
                //JSONArray totalStore = jsonObject . getJSONArray ("stores");

                val sharedPref = activity?.getPreferences( Context.MODE_PRIVATE)?: return
                with (sharedPref.edit()) {
                    putString("access_token", jsonObject.get("token").toString())
                    apply()
                }
                println(sharedPref.getString("access_token", ""))
                passDataToParent2(sharedPref.getString("access_token", "").toString())
               /* val intent = Intent(context, ScanFragment::class.java)
                startActivity(intent)*/
                //findNavController().navigate(R.id.navigation_paymentDialog)
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, PayementDialog()).commit()

            }
            })




    }

    /*private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }*/

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    interface DataPassListener2{
        fun onDataPassed2(data: String)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}