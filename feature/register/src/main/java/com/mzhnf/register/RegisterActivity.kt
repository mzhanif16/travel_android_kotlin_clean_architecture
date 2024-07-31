package com.mzhnf.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mzhnf.register.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var passwordVisible = false
    private val registerViewModel: RegisterViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val password = binding.edtPassword
        val email = binding.edtEmail
        val username = binding.edtUsername
        registerViewModel.regisResponse.observe(this){
            if (it!=null) {
                if (it.success != null && it.success.meta.code == 201) {
                    Snackbar.make(binding.root, it.success.meta.message, Snackbar.LENGTH_SHORT).show()
                }
                if (it.error.isNotEmpty()){
                    Snackbar.make(binding.root, it.error, Snackbar.LENGTH_SHORT).show()
                    Log.d("ERRORNYA",it.error)
                }
            }
        }
        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val value = p0.toString()
                if (value.isEmpty()) {
                    binding.tvErrorUsername.visibility = View.VISIBLE
                } else {
                    binding.tvErrorUsername.visibility = View.GONE
                }
            }

        })
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val value = p0.toString()
                if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
                    binding.tvErrorEmail.visibility = View.VISIBLE
                    binding.tvErrorEmail.text = if (value.isEmpty()) {
                        "Please input email address"
                    } else {
                        "Invalid email format"
                    }
                } else {
                    binding.tvErrorEmail.visibility = View.GONE
                }
            }

        })
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val value = p0.toString()
                if (value.isEmpty() || value.length < 4) {
                    binding.tvErrorPassword.visibility = View.VISIBLE
                    binding.tvErrorPassword.text = if (value.isEmpty()) {
                        "Please input password"
                    } else {
                        "Minimum password is 4 characters"
                    }
                } else {
                    binding.tvErrorPassword.visibility = View.GONE
                }
            }

        })
        password.setOnTouchListener { _, event ->
            val Right = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= password.right - password.compoundDrawables[Right].bounds.width()) {
                    val selection = password.selectionEnd
                    if (passwordVisible) {
                        // set drawable image here
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            com.mzhnf.common.R.drawable.baseline_visibility_off_24,
                            0
                        )
                        // for hide password
                        password.transformationMethod = PasswordTransformationMethod.getInstance()
                        passwordVisible = false
                    } else {
                        // set drawable image here
                        password.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            com.mzhnf.common.R.drawable.baseline_visibility_24,
                            0
                        )
                        // for show password
                        password.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                        passwordVisible = true
                    }
                    password.setSelection(selection)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
        binding.btnOtp.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            if(email.isEmpty() || username.isEmpty() || password.isEmpty()){
                Snackbar.make(binding.root, "Please fill in the fields that are still empty", Snackbar.LENGTH_SHORT).show()
            }else{
                registerViewModel.register(email, username, password)
            }
        }

    }
}