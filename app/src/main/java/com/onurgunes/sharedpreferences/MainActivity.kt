package com.onurgunes.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.onurgunes.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

lateinit var  sharedPreferences: SharedPreferences
var recivedname : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

     sharedPreferences = this.getSharedPreferences("com.onurgunes.sharedpreferences",Context.MODE_PRIVATE)

        recivedname = sharedPreferences.getString("nick","")
        if (recivedname == "") {
            binding.textView.text = "Nickname:"

        } else {
            binding.textView.text = "Nickname:${recivedname}"

        }





    }

    fun save(view: View) {

        val entrtnick = binding.editText.text.toString()
        if (entrtnick == "") {
            Toast.makeText(this,"ENTER A NAME",Toast.LENGTH_LONG).show()

        }
        sharedPreferences.edit().putString("nick",entrtnick).apply()
        binding.textView.text = "Nickname:${entrtnick}"



    }



    fun delete(view: View) {
        recivedname = sharedPreferences.getString("nick","")

        if (recivedname != "") {
            sharedPreferences.edit().remove("nick").apply()

        } else {
            binding.textView.text = "Nickname:"
        }


    }


}