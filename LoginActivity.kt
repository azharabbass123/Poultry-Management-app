package com.example.poultrymanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*

const val EXTRA_MESSAGE = "com.example.myApp.MESSAGE"
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btnsignup.setOnClickListener() {
            val intentUser = Intent(this, RegistrationActivity::class.java)
            //starting the activity
            startActivity(intentUser)
        }
    }



    fun loginEmployee(view: View) {

        var etEmailL = findViewById(R.id.etusernameL) as EditText
        var etPassL = findViewById(R.id.etpasswordL) as EditText
        if (etusernameL.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter Username and Password", Toast.LENGTH_LONG).show()
        } else {


            var loginId: String = etusernameL.text.toString()
            var loginPass: String = etpasswordL.text.toString()
            GVar.appUser = loginId

            var rq: RequestQueue = Volley.newRequestQueue(this)

            var url: String =
                "http://" + GVar.myipaddress + "/FTPHP/loginEmployee.php?username=" + etusernameL.text.toString() + "&password=" + etpasswordL.text.toString()


            var sr = StringRequest(Request.Method.GET, url, { response ->


                when (response?.trim()) {

                    "1" -> {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        val message = "Welcome " + loginId
                        val intentlogin = Intent(this, HomePageActivity::class.java).apply {
                            putExtra(EXTRA_MESSAGE, message)
                        }
                        startActivity(intentlogin)

                        etusernameL.setText("")
                        etpasswordL.setText("")
                        etusernameL.requestFocus()
                    }

                    "0" -> {
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                    }
                }
            }, { error ->

                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })


            rq.add(sr)

        }

    }

}
