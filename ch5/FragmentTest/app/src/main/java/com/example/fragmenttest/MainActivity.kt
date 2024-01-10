package com.example.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.fragmenttest.databinding.ActivityMainBinding
import com.example.fragmenttest.databinding.LeftFragmentBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 为什么这里的button onClick不会回调
        // GPT回答：
        // 在onCreate方法中，你通过LeftFragmentBinding.inflate(layoutInflater)创建了一个LeftFragment的实例
        // （实例A），并为其内部的按钮设置了点击监听。然而，这个Fragment的实例并没有被添加到你的Activity中，因此按钮
        // 的点击事件也就无法被捕获。
        // 同时在XML布局文件中，通过<fragment android:name="com.example.fragmenttest.LeftFragment" />这行
        // 代码，你又创建了另一个LeftFragment的实例（实例B）并添加到了Activity中。虽然实例B已经添加到Activity并可
        // 见，但其按钮并未设置点击监听。
        // 简而言之，你的按钮监听被设置在了实例A的按钮上，但实际上你看到并且进行操作的却是实例B的按钮，这就导致了你所描
        // 述的问题。
//        val leftFragmentBinding = LeftFragmentBinding.inflate(layoutInflater)
//        leftFragmentBinding.button.setOnClickListener {
//            Log.d(TAG, "button clicked.")
//            replaceFragment(AnotherRightFragment())
//        }
        replaceFragment(RightFragment())
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}