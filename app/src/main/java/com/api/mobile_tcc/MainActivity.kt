package com.api.mobile_tcc

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.api.mobile_tcc.databinding.ActivityMainBinding
import com.api.mobile_tcc.model.MyUser
import com.api.mobile_tcc.retrofit.MyUserRetrofit
import com.api.mobile_tcc.retrofit.RetrofitServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody
import java.util.logging.Level
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: RetrofitServer
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = RetrofitServer()

        binding.checkboxShowPassword.setOnClickListener {
            togglePasswordVisibility()
        }

        binding.signUp.setOnClickListener {
            registerNewUser()
        }

        binding.forgotPassword.setOnClickListener {
            forgotPassword()
        }
    }

    @Override
    private fun togglePasswordVisibility() {
        if (binding.checkboxShowPassword.isChecked) {
            binding.editTextPassword.transformationMethod = null
        } else {
            binding.editTextPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    fun loginOnClick(view: View) {
        if (view == binding.btnLogin) {
            val email: String = binding.editTextEmail.text.toString()
            val password: String = binding.editTextPassword.text.toString()

            val myUser = MyUser()
            myUser.setEmail(email)
            myUser.setPassword(password)

            authenticatorUser(myUser)
        }
    }

    private fun authenticatorUser(myUser: MyUser) {
        val myUserRetrofit: MyUserRetrofit = retrofit.getAuthUser()
        val call: Call<ResponseBody> = myUserRetrofit.authenticatorUser(myUser)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    startActivity(Intent(this@MainActivity, Home::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error during authentication", Toast.LENGTH_SHORT)
                    .show()
                Logger.getLogger(MainActivity::class.java.name).log(Level.SEVERE, "ERROR", t)
            }
        })
    }

    fun forgotPassword() {
        val url = "http://192.168.15.15:8080/forgot_password"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun registerNewUser() {
        val url = "http://192.168.15.15:8080/register"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}