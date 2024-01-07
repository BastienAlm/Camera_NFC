package com.gotham.cashmanager.data

import com.gotham.cashmanager.data.model.LoggedInUser
import okhttp3.FormBody
import okhttp3.RequestBody
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private val client = OkHttpClient()
    fun login(username: String, password: String): Result<LoggedInUser> {
        try {

            val body: RequestBody = FormBody.Builder().add("email",username).add("password", password).build()

            println(body)
            val request: Request = Request.Builder()
                .url("http://10.0.2.2:8080/auth/login")
                .post(body)
                .build()
            println(request)

          var result: Response =  client.newCall(request).execute()
            println(result)

           var fakeUser = LoggedInUser(result)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }


    fun logout() {
        // TODO: revoke authentication
    }
}