package com.example.contactstest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyProvider : ContentProvider() {

    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    companion object {
        const val AUTHORITY = "com.example.app.provider"

        const val DIR_PREFIX = "vnd.android.cursor.dir/"
        const val ITEM_PREFIX = "vnd.android.cursor.item/"
    }

    init {
        uriMatcher.addURI(AUTHORITY, "table1", table1Dir)
        uriMatcher.addURI(AUTHORITY, "table1/#", table1Item)
        uriMatcher.addURI(AUTHORITY, "table2", table2Dir)
        uriMatcher.addURI(AUTHORITY, "table2/#", table2Item)
    }

    override fun onCreate(): Boolean {
        TODO("Not yet implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            table1Dir -> {

            }
            table1Item -> {

            }
            table2Dir -> {

            }
            table2Item -> {

            }
        }
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri) = when (uriMatcher.match(uri)) {
        table1Dir -> "$DIR_PREFIX$AUTHORITY.table1"
        table1Item -> "$ITEM_PREFIX$AUTHORITY.table1"
        table2Dir -> "$DIR_PREFIX$AUTHORITY.table2"
        table2Item -> "$ITEM_PREFIX$AUTHORITY.table2"
        else -> null
    }

}