package com.gotham.cashmanager

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gotham.cashmanager.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.prefs.Preferences


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    //val JSON: MediaType = MediaType.get("application/json; charset=utf-8")


    private val client = OkHttpClient()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

       // run("https://api.github.com/users/Evin1-/repos")
        //login("dupont@paris.com", "dupont3")

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_scan, R.id.navigation_shop, R.id.navigation_settings, R.id.navigation_cart, R.id.navigation_login, R.id.navigation_paymentDialog
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }

    /*fun login(username: String, password: String) {

       val body: RequestBody = FormBody.Builder().add("email",username).add("password", password).build()

       println(body)
        val request: Request = Request.Builder()
            .url("http://10.0.2.2:8080/auth/login")
            .post(body)
            .build()
        println(request)

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) = println(e)
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
        }*/

}
