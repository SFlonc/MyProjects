package com.rmaes.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDataBase(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME="mydatabase.db"
        private const val DATABASE_VERSION=1
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE "+MyDatabaseInfo.TABLE_NAME+" ("+MyDatabaseInfo.COLUMN_ID+" INTEGER PRIMARY KEY, "+
                    MyDatabaseInfo.COLUMN_NAME_USER_NAME+" TEXT , "+MyDatabaseInfo.COLUMN_NAME_CASH+" INTEGER)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+MyDatabaseInfo.TABLE_NAME
        private const val LOG_TAG = "SF_LOG"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(LOG_TAG, "Upgrading database from version $oldVersion to $newVersion which will destroy all old data")
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

}