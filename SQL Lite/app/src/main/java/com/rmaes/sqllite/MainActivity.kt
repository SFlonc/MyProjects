package com.rmaes.sqllite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    private val theMyDataBase : MyDataBase = MyDataBase(this)
    private var theSQLiteDatabase:SQLiteDatabase? = null

    private lateinit var theSeekBar: SeekBar
    private lateinit var theEditText: EditText
    private lateinit var theEditText2: EditText

    companion object{ private const val LOG_TAG = "SF_LOG" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        theSQLiteDatabase=theMyDataBase.writableDatabase

        theSeekBar=findViewById(R.id.seekBar)
        theSeekBar.max = 100
        theEditText=findViewById(R.id.text_name)
        theEditText2=findViewById(R.id.text_id)
    }

    fun onClick_insert(view: View) {
        val anProgress = theSeekBar.progress
        Log.i(LOG_TAG,"Progress: $anProgress")

        val astrText = theEditText.text.toString()
        val aMapValues = ContentValues()
        aMapValues.put(MyDatabaseInfo.COLUMN_NAME_USER_NAME, astrText)
        aMapValues.put(MyDatabaseInfo.COLUMN_NAME_CASH, anProgress)

        val aNewRowId =  theSQLiteDatabase?.insert(MyDatabaseInfo.TABLE_NAME,null ,aMapValues)
        Log.i(LOG_TAG,"NewRowId: $aNewRowId")
    }
    fun onClick_read_all(view: View) {
        val avstrProject = arrayOf(MyDatabaseInfo.COLUMN_ID, MyDatabaseInfo.COLUMN_NAME_USER_NAME, MyDatabaseInfo.COLUMN_NAME_CASH)
        val astrSortOrder = MyDatabaseInfo.COLUMN_NAME_USER_NAME + "DESC"

        val aCursor =   theSQLiteDatabase?.query(
            MyDatabaseInfo.TABLE_NAME, avstrProject, null, null, null, null, astrSortOrder)

        displayValues(aCursor)
    }

    private fun displayValues(aCursor: Cursor?) {
        if(aCursor === null) {
            Log.e(LOG_TAG, "cursor:error")
        } else if(!aCursor.moveToFirst()){
            Log.i(LOG_TAG, "cursor:empty")
        }else{
            val anColumnIndex_Id = aCursor.getColumnIndex(MyDatabaseInfo.COLUMN_ID)
            val anColumnIndex_User_Name = aCursor.getColumnIndex(MyDatabaseInfo.COLUMN_NAME_USER_NAME)
            val anColumnIndex_Cash = aCursor.getColumnIndex(MyDatabaseInfo.COLUMN_NAME_CASH)
            var aID:Long
            var astrUsername:String
            var aCash:Int

            do{
                aCursor.run{
                    aID = getLong(anColumnIndex_Id)
                    astrUsername = getString(anColumnIndex_User_Name)
                    aCash= getInt(anColumnIndex_Cash)
                }
                Log.i(LOG_TAG,"ID: $aID; UserName: $astrUsername Cash: $aCash")
            }while(aCursor.moveToNext())
        }
    }

    fun onClick_delete(view: View) {
        val astrSelection: String = MyDatabaseInfo.COLUMN_ID + "=?"
        val avstrSelectionArgs = arrayOf(theEditText2.text.toString())

        val anRowsAffected = theSQLiteDatabase?.delete(MyDatabaseInfo.TABLE_NAME,astrSelection,avstrSelectionArgs)
        Log.i(LOG_TAG,"RowsAffected: $anRowsAffected")
    }
    fun onClick_update(view: View) {
        val astrSelection : String  = MyDatabaseInfo.COLUMN_ID +"=?"
        val avstrSelectionArgs = arrayOf(theEditText2.text.toString())

        val astrText = theEditText.text.toString()

        val aMapValues = ContentValues()
        aMapValues.put(MyDatabaseInfo.COLUMN_NAME_USER_NAME, astrText)

        val anRowsAffected = theSQLiteDatabase?.update(MyDatabaseInfo.TABLE_NAME, aMapValues,astrSelection,avstrSelectionArgs)
        Log.i(LOG_TAG, "RowsAffected: $anRowsAffected")
    }
}