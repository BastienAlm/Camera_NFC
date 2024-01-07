package com.gotham.cashmanager.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import com.gotham.cashmanager.MainActivity
import com.gotham.cashmanager.R
import com.gotham.cashmanager.databinding.ActivityLoginBinding
import com.gotham.cashmanager.ui.scan.ScanFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {


    private var _binding: ActivityLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val client = OkHttpClient()

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



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

                val sharedPref = getPreferences( Context.MODE_PRIVATE)?: return
                with (sharedPref.edit()) {
                    putString("access_token", jsonObject.get("token").toString())
                    apply()
                }
                println(sharedPref.getString("access_token", ""))
              //  passDataToParent2(sharedPref.getString("access_token", "").toString())
                openYourActivity()
                // getFragmentManager()?.popBackStack()
                //findNavController().navigate(R.id.navigation_scan)
                //requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, ScanFragment()).commit()
            }
        })




    }

    fun openYourActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    }

