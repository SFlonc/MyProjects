package com.rmaes.recyclerviewapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val ContactList = arrayListOf<String>()
class MainActivity : AppCompatActivity() {
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)//ustawia widoki w formie listy
        recyclerView.adapter = MyAdapter() //adapter laczy baze danych z widokiem

        val contentResolver = contentResolver
        //dostarcza dostepu do modelu zawartości
        //contentProviders dostarcza tresci apce zz innej apki
        //contentResolver za pozwoleniem bierze dane od contentProviders; coś jak magazynier z kurierem
        //cursor zwroci tabele kontaktow,
        //Cursor wskazuje na elementy,wskazuje na konkretny rekord [wiersz] w tabeli
        contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null, null)?.use { cursor ->
            cursor.moveToFirst()
            while (!cursor.isAfterLast) { //jezeli cursor  nie jest za ostatnim rekordem
                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                ContactList.add(name)
                cursor.moveToNext()
            }
        }
    }
}