package com.charder.navigationdrawerrecycler

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charder.navigationdrawerrecycler.`class`.*
import com.google.android.material.navigation.NavigationView
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class LoginActivity : AppCompatActivity() {

    var navigationView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null
    var toolbar : Toolbar? = null

    lateinit var rv_drawer : RecyclerView
    lateinit var drawerAdapter : DrawerAdapter

    lateinit var iv_pic : ImageView
    lateinit var iv_message : ImageView
    lateinit var tv_notice : ImageView
    lateinit var tv_name : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        navigationView = findViewById(R.id.navigationView)
        rv_drawer = navigationView!!.findViewById(R.id.rv_drawer)
        rv_drawer.layoutManager = LinearLayoutManager(this)
        drawerAdapter = DrawerAdapter(this , drawerList)
        rv_drawer.adapter = drawerAdapter
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

    inner class DrawerAdapter(val _activity : Activity, val drawerItemList :MutableList<DrawerItem>) : RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder>() {

        var clickIndex = -1
        inner class DrawerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
            val iv_icon = itemView.findViewById<ImageView>(R.id.iv_icon)
            val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
            val rv_inner = itemView.findViewById<RecyclerView>(R.id.rv_inner)
            lateinit var drawerItemAdapter : DrawerItemAdapter
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
            return DrawerViewHolder(
                LayoutInflater.from(parent?.context).inflate(R.layout.item_drawer, parent, false)
            )
        }

        override fun getItemCount(): Int { return drawerItemList.size }


        override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
            val drawerItem = drawerItemList.get(position)
            holder.tv_title.setText(drawerItem.title)
            holder.iv_icon.setImageResource(drawerItem.image)
            holder.rv_inner.layoutManager = LinearLayoutManager(_activity)
            holder.drawerItemAdapter = DrawerItemAdapter(_activity , drawerItem.items)
            holder.rv_inner.adapter = holder.drawerItemAdapter
            if (clickIndex == position){
                holder.rv_inner.visibility = View.VISIBLE
            }else{
                holder.rv_inner.visibility = View.GONE
            }
            holder.itemView.setOnClickListener {
                clickIndex = position
                notifyDataSetChanged()
            }
        }

        inner class DrawerItemAdapter(val _activity : Activity, val drawerItemInnerList :MutableList<DrawerItemInner>) : RecyclerView.Adapter<DrawerItemAdapter.DrawerItemViewHolder>() {

            inner class DrawerItemViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

                val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
            }
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerItemViewHolder {
                return DrawerItemViewHolder(
                    LayoutInflater.from(parent?.context).inflate(R.layout.item_drawer_inner, parent, false)
                )
            }

            override fun getItemCount(): Int { return drawerItemInnerList.size }


            override fun onBindViewHolder(holder: DrawerItemViewHolder, position: Int) {
                val drawerItemInner = drawerItemInnerList.get(position)
                holder.tv_title.setText(drawerItemInner.title)
                holder.itemView.setOnClickListener {
                    val navController = Navigation.findNavController(_activity, R.id.nav_host_fragment)
                    navController.navigate(drawerItemInner.id)
                }
            }

        }
    }
}