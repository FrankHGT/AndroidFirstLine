package com.example.uicustomviews

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title, this);

        var backBtn: Button = findViewById(R.id.titleBack)
        backBtn.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }

        var editBtn: Button = findViewById(R.id.titleEdit)
        editBtn.setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_SHORT).show()
        }
    }
}