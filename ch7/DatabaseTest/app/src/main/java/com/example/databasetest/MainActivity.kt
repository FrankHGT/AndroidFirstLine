package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.databasetest.databinding.ActivityMainBinding
import java.lang.Exception
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        binding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
        binding.addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)
        }
        binding.updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }
        binding.deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }
        binding.queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d(TAG, "book name is $name")
                    Log.d(TAG, "book author is $author")
                    Log.d(TAG, "book pages is $pages")
                    Log.d(TAG, "book price is $price")
                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        binding.replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book", null, null)
                val values = cvOf(
                    "name" to "Game of Thrones",
                    "author" to "George Martin",
                    "pages" to 720,
                    "price" to 20.85
                )
                db.insert("Book", null, values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }
}