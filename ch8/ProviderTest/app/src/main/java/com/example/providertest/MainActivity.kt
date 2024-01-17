package com.example.providertest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.contentValuesOf
import com.example.providertest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    object ProviderProxy {
        private const val PROVIDER_AUTHORITY = "com.example.databasetest.provider"
        private const val CONTENT_PREFIX = "content://"

        fun getBookTableUriStr() = "$CONTENT_PREFIX$PROVIDER_AUTHORITY/book"
        fun getBookItemUriStrById(id: String) = "$CONTENT_PREFIX$PROVIDER_AUTHORITY/book/$id"
    }

    private lateinit var binding: ActivityMainBinding

    var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addData.setOnClickListener {
            val uri = Uri.parse(ProviderProxy.getBookTableUriStr())
            val values = contentValuesOf("name" to "A Clash of Kings",
                "author" to "George Martin",
                "pages" to 1040,
                "price" to 22.85)
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }
        binding.queryData.setOnClickListener {
            val uri = Uri.parse(ProviderProxy.getBookTableUriStr())
            contentResolver.query(uri, null, null, null, null)
                ?.build {
                while (moveToNext()) {
                    val name = getString(getColumnIndexOrThrow("name"))
                    val author = getString(getColumnIndexOrThrow("author"))
                    val pages = getString(getColumnIndexOrThrow("pages"))
                    val price = getString(getColumnIndexOrThrow("price"))
                    Log.d(TAG, "book name is $name")
                    Log.d(TAG, "book author is $author")
                    Log.d(TAG, "book pages is $pages")
                    Log.d(TAG, "book price is $price")
                }
                close()
            }
        }
        binding.updateData.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse(ProviderProxy.getBookItemUriStrById(it))
                val values = contentValuesOf("name" to "A Storm of Swords",
                    "pages" to 1216,
                    "price" to 24.05)
                contentResolver.update(uri, values, null, null)
            }
        }
        binding.deleteData.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse(ProviderProxy.getBookItemUriStrById(it))
                contentResolver.delete(uri, null, null)
            }
        }
    }
}