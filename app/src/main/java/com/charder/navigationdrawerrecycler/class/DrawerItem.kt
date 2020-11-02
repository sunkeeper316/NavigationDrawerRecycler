package com.charder.navigationdrawerrecycler.`class`

class DrawerItem(title : String , image : Int)  {
    var title : String = title
    var image : Int = image
    var items : MutableList<DrawerItemInner> = arrayListOf()
}