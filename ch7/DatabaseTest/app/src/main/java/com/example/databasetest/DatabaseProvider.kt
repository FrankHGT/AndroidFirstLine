package com.example.databasetest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class DatabaseProvider : ContentProvider() {

    companion object {
        private const val BOOK_DIR = 0
        private const val BOOK_ITEM = 1
        private const val CATEGORY_DIR = 2
        private const val CATEGORY_ITEM = 3
        private const val AUTHORITY = "com.example.databasetest.provider"

        private const val DIR_PREFIX = "vnd.android.cursor.dir/"
        private const val ITEM_PREFIX = "vnd.android.cursor.item/"

        private const val TABLE_BOOK = "book"
        private const val TABLE_CATEGORY = "category"
    }

    private var dbHelper: MyDatabaseHelper? = null

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(AUTHORITY, "book", BOOK_DIR)
        matcher.addURI(AUTHORITY, "book/#", BOOK_ITEM)
        matcher.addURI(AUTHORITY, "category", CATEGORY_DIR)
        matcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM)
        matcher
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = dbHelper?.let {
        val db = it.writableDatabase
        val deletedRows = when (uriMatcher.match(uri)) {
            BOOK_DIR -> db.delete("Book", selection, selectionArgs)
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                db.delete("Book", "id = ?", arrayOf(bookId))
            }
            CATEGORY_DIR -> db.delete("Category", selection, selectionArgs)
            CATEGORY_ITEM -> {
                val categoryId = uri.pathSegments[1]
                db.delete("Category", "id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        deletedRows
    } ?: 0

    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
        BOOK_DIR -> "$DIR_PREFIX$AUTHORITY.$TABLE_BOOK"
        BOOK_ITEM -> "$ITEM_PREFIX$AUTHORITY.$TABLE_BOOK"
        CATEGORY_DIR -> "$DIR_PREFIX$AUTHORITY.$TABLE_CATEGORY"
        CATEGORY_ITEM -> "$ITEM_PREFIX$AUTHORITY.$TABLE_CATEGORY"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
        val db = it.writableDatabase
        val res = when (uriMatcher.match(uri)) {
            BOOK_DIR, BOOK_ITEM -> {
                val newBookId = db.insert("Book", null, values)
                Uri.parse("content://$AUTHORITY/book/$newBookId")
            }
            CATEGORY_DIR, CATEGORY_ITEM -> {
                val newCategoryId = db.insert("Category", null, values)
                Uri.parse("content://$AUTHORITY/category/$newCategoryId")
            }
            else -> null
        }
        res
    }

    override fun onCreate() = context?.let {
        dbHelper = MyDatabaseHelper(it, "BookStore.db", 2)
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper?.let {
        val db = it.readableDatabase
        val cursor = when (uriMatcher.match(uri)) {
            BOOK_DIR -> db.query("Book", projection, selection,
                selectionArgs, null, null, sortOrder)
            BOOK_ITEM -> {
                val bookId = uri.pathSegments[1]
                db.query("Book", projection, "id = ?",
                    arrayOf(bookId), null, null, sortOrder)
            }
            CATEGORY_DIR -> db.query("Category", projection, selection,
                selectionArgs, null, null, sortOrder)
            CATEGORY_ITEM -> {
                val categoryId = uri.pathSegments[1]
                db.query("Category", projection, "id = ?",
                    arrayOf(categoryId), null, null, sortOrder)
            }
            else -> null
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {
        val db = it.writableDatabase
        val updatedRows = when (uriMatcher.match(uri)) {
            BOOK_DIR -> db.update("Book", values, selection, selectionArgs)
            BOOK_ITEM -> {
                val updateBookId = uri.pathSegments[1]
                db.update("Book", values, "id = ?", arrayOf(updateBookId))
            }
            CATEGORY_DIR -> db.update("Category", values, selection, selectionArgs)
            CATEGORY_ITEM -> {
                val updateCategoryId = uri.pathSegments[1]
                db.update("Category", values, "id = ?", arrayOf(updateCategoryId))
            }
            else -> 0
        }
        updatedRows
    } ?: 0
}