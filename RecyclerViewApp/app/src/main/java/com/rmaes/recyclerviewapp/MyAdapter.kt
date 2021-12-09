package com.rmaes.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(viewGrop: ViewGroup, property1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGrop.context)
        val contactRow = layoutInflater.inflate(R.layout.contact_row, viewGrop, false)
        return MyViewHolder(contactRow)
    }

    override fun getItemCount(): Int {
        return ContactList.size //ile kontaktow mamy stworzyc ?
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { //metoda aktualizujaca widoki
        val name = holder.view.findViewById<TextView>(R.id.tv_contact_name)
        name.text = ContactList[position]
    }
}
class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view)