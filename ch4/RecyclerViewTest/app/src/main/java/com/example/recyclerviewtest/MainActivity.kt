package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFruits()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
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