package com.example.fragmentbestpractice

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fragmentbestpractice.databinding.ActivityNewsContentBinding

class NewsContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsContentBinding

    companion object {
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")

        if (title != null && content != null) {
            // fragment不是view, 不能用findViewById寻找
            val fragment = supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
            fragment.refresh(title, content)
        }
    }
}