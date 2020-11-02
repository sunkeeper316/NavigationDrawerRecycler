package com.charder.navigationdrawerrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var bt_login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_login = findViewById(R.id.bt_login)
        bt_login.setOnClickListener {
            startActivity(Intent().setClass(this, LoginActivity::class.java))
            finish()
        }
    }
}