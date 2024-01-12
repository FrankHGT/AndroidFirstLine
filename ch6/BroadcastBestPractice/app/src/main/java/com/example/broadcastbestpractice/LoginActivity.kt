package com.example.broadcastbestpractice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.broadcastbestpractice.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            binding.apply {
                val account = prefs.getString("account", "")
                val password = prefs.getString("password", "")
                accountEdit.setText(account)
                passwordEdit.setText(password)
                rememberPassword.isChecked = true
            }
        }
        binding.apply {
            login.setOnClickListener {
                val account = accountEdit.text.toString()
                val password = passwordEdit.text.toString()

                if (account == "frank" && password == "111111") {
                    val editor = prefs.edit()
                    if (rememberPassword.isChecked) {
                        editor.putBoolean("remember_password", true)
                        editor.putString("account", account)
                        editor.putString("password", password)

                    } else {
                        editor.clear()
                    }
                    editor.apply()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "account or password is invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}