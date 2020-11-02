package com.charder.navigationdrawerrecycler

import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.charder.navigationdrawerrecycler.`class`.currentToolbar
import com.google.android.material.navigation.NavigationView
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class LoginActivity : AppCompatActivity() {

    var navigationView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var toolbar : Toolbar? = null

    lateinit var iv_pic : ImageView
    lateinit var iv_message : ImageView
    lateinit var tv_notice : ImageView
    lateinit var tv_name : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpActionBar()
        initDrawer()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navigationView!!, navController)
//        tabActivity = this
    }

    private fun setUpActionBar() {
        toolbar = findViewById(R.id.toolbar)
        currentToolbar = toolbar
//        iv_pic = toolbar!!.findViewById(R.id.iv_pic)
//        iv_message = toolbar!!.findViewById(R.id.iv_message)
//        tv_notice = toolbar!!.findViewById(R.id.tv_notice)
//        tv_name = toolbar!!.findViewById(R.id.tv_name)

//        iv_message.setOnClickListener {
//            val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//            navController.navigate(R.id.messageOverviewFragment)
//        }
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun initDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        // 建立ActionBarDrawerToggle監聽器，監聽抽屜開關的狀態
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.textOpen, R.string.textClose)
        // 檢查裝置是否為M(Marshmallow, Android 6.0)以決定要採用新/舊的方法來加上抽屜監聽器
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawerLayout?.run { addDrawerListener(actionBarDrawerToggle) }

        } else {
            drawerLayout?.run{ setDrawerListener(actionBarDrawerToggle)}
        }
        // 將左上角按鈕動畫與抽屜選單開關同步化
        actionBarDrawerToggle.syncState()
        navigationView = findViewById(R.id.navigationView)

//        navigationView!!.menu.clear()
//        navigationView!!.inflateMenu(R.menu.drawer_menu)
    }

    // 點擊標題列上的按鈕會呼叫此方法
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout!!.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout!!.openDrawer(GravityCompat.START)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}