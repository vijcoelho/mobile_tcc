package com.api.mobile_tcc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import at.favre.lib.crypto.bcrypt.BCrypt
import com.api.mobile_tcc.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            authenticateUser(email, password)
        }
    }

    private fun authenticateUser(email: String, password: String) {
        val client = OkHttpClient()

        val requestBody = FormBody.Builder()
            .add("email", email)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url("http://192.168.15.15:8080/login")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(applicationContext, "Erro on connect in the server", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody: String? = response.body?.use { it.string() }
                if (response.isSuccessful && responseBody != null) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Welcome", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, Home::class.java))
                        finish()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}