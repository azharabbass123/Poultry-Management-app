package com.example.poultrymanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btnR.setOnClickListener(){
            registerEmployee()
        }
    }
    private fun registerEmployee() {
        if (etName.text.toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Data", Toast.LENGTH_LONG).show()
        } else {


            if (etPass.text.toString().equals(etConPass.text.toString())) {

                var url: String =
                    "http://" + GVar.myipaddress + "/FTPHP/addEmployee.php?empname=" + etName.text.toString() + "&address=" + etaddress.text.toString() + "&username=" + etusername.text.toString() + "&cnic=" + etcnic.text.toString() + "&password=" + etPass.text.toString()
                var rq: RequestQueue = Volley.newRequestQueue(this)

                var sr = StringRequest(
                    Request.Method.GET, url, { response ->
                        if (response.trim() == "Success") {


                            Toast.makeText(this, "Registeration successful", Toast.LENGTH_LONG)
                                .show()
                            etName.setText("")
                            etusername.setText("")
                            etaddress.setText("")
                            etcnic.setText("")
                            etPass.setText("")
                            etConPass.setText("")
                            etName.requestFocus()

                            val intentUser = Intent(this, HomePageActivity::class.java)
                            //starting the activity

                            startActivity(intentUser)

                        } else {
                            Toast.makeText(this, "User Registration Failed", Toast.LENGTH_LONG)
                                .show()

                        }

                    },
                    { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

                    })

                rq.add(sr)
            } else {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show()
            }


        }
    }

}