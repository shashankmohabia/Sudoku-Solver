package com.example.sudokusolver

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.sudoku_item.view.*

class SudokuViewAdapter(var context: Context, var numberList: ArrayList<Int>) : BaseAdapter() {

    override fun getCount(): Int {
        return numberList.size
    }

    override fun getItem(position: Int): Any {
        return numberList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val number = this.numberList[position]
        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val numberView = inflator.inflate(R.layout.sudoku_item, null)
        numberView.sudoku_number.text = number.toString()
        return numberView
    }
}