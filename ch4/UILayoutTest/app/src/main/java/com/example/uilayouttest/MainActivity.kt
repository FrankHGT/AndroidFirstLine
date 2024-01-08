package com.example.uilayouttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button: Button = findViewById(R.id.button1)
        button.setOnClickListener{
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        var button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            var intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }

        var button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            var intent = Intent(this, FrameLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}