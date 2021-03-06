package com.charder.navigationdrawerrecycler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.charder.navigationdrawerrecycler.`class`.*

class MainActivity : AppCompatActivity() {

    lateinit var bt_login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAllDrawer()
        bt_login = findViewById(R.id.bt_login)
        bt_login.setOnClickListener {
            startActivity(Intent().setClass(this, LoginActivity::class.java))
            finish()
        }
    }
    fun setAllDrawer(){
        setItem()
        drawerList.add(drawerItemHome)
        drawerList.add(drawerItemClassRoom)
        drawerList.add(drawerItemMember)
        drawerList.add(drawerItemSetting)
        drawerList.add(drawerItemLogout)
    }
    fun setItem(){
        drawerItemHome.items.add(drawerItemInnerHome)

        drawerItemClassRoom.items.add(drawerItemInnerClassRoom)
        drawerItemClassRoom.items.add(drawerItemInnerTeacher)

        drawerItemMember.items.add(drawerItemInnerMember)

        drawerItemSetting.items.add(drawerItemInnerSetting)
    }
}