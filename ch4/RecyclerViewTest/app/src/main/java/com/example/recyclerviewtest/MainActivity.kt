package com.example.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFruits()
        // 1. 另一个方向的项数
        // 2. 主排列方向
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit(getRandomLengthString("Apple"), R.drawable.ic_launcher_background))
            fruitList.add(Fruit(getRandomLengthString("Banana"), R.drawable.ic_launcher_foreground))
            fruitList.add(Fruit(getRandomLengthString("Orange"), androidx.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"), androidx.appcompat.R.drawable.abc_list_selector_holo_light))
            fruitList.add(Fruit(getRandomLengthString("Pear"), androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_000))
            fruitList.add(Fruit(getRandomLengthString("Grape"), androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015))
            fruitList.add(Fruit(getRandomLengthString("Pineapple"), androidx.appcompat.R.drawable.abc_btn_radio_material))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"), androidx.appcompat.R.drawable.abc_ic_menu_share_mtrl_alpha))
            fruitList.add(Fruit(getRandomLengthString("Cherry"), androidx.appcompat.R.drawable.abc_ic_voice_search_api_material))
            fruitList.add(Fruit(getRandomLengthString("Mango"), androidx.appcompat.R.drawable.abc_spinner_textfield_background_material))
        }
    }

    // 重复随机次数str，使得各子项的高度不一致，体现出瀑布流的效果
    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}