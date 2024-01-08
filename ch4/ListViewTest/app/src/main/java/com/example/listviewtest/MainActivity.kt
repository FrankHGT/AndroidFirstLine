package com.example.listviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFruits()
        val adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        var listView: ListView = findViewById(R.id.listView)
        listView.adapter = adapter

        // ListView点击事件
        listView.setOnItemClickListener { _, _, position, _ ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit("Apple", R.drawable.ic_launcher_background))
            fruitList.add(Fruit("Banana", R.drawable.ic_launcher_foreground))
            fruitList.add(Fruit("Orange", androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha))
            fruitList.add(Fruit("Watermelon", androidx.appcompat.R.drawable.abc_list_selector_holo_light))
            fruitList.add(Fruit("Pear", androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_000))
            fruitList.add(Fruit("Grape", androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015))
            fruitList.add(Fruit("Pineapple", androidx.appcompat.R.drawable.abc_btn_radio_material))
            fruitList.add(Fruit("Pineapple", androidx.appcompat.R.drawable.abc_btn_radio_to_on_mtrl_015))
            fruitList.add(Fruit("Strawberry", androidx.appcompat.R.drawable.abc_ic_menu_share_mtrl_alpha))
            fruitList.add(Fruit("Cherry", androidx.appcompat.R.drawable.abc_ic_voice_search_api_material))
            fruitList.add(Fruit("Mango", androidx.appcompat.R.drawable.abc_spinner_textfield_background_material))
        }
    }
}