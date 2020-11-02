package com.charder.navigationdrawerrecycler.`class`

import androidx.appcompat.widget.Toolbar
import com.charder.navigationdrawerrecycler.R


var currentToolbar : Toolbar? = null

var drawerList = arrayListOf<DrawerItem>()

val drawerItemHome = DrawerItem("首頁" , R.drawable.system_notification)
val drawerItemClassRoom = DrawerItem("教室" , R.drawable.classroom)
val drawerItemMember = DrawerItem("人員" , R.drawable.account)
val drawerItemSetting = DrawerItem("設定" , R.drawable.setting)
val drawerItemLogout = DrawerItem("登出" , R.drawable.logout)

val drawerItemInnerHome = DrawerItemInner("首頁" , R.id.homeFragment)
val drawerItemInnerClassRoom = DrawerItemInner("教室設定" , R.id.classRoomFragment)
val drawerItemInnerTeacher = DrawerItemInner("老師設定" , R.id.teacherFragment)
val drawerItemInnerMember = DrawerItemInner("人員設定" , R.id.memberFragment)
val drawerItemInnerSetting = DrawerItemInner("設定" , R.id.settingFragment)