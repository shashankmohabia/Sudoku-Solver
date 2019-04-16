package com.example.sudokusolver

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.sudoku_item.view.*

class SudokuViewAdapter(private var context: Context, var numberList: ArrayList<Int>) : BaseAdapter() {

    val colourList = arrayOf(
        0,
        1,
        2,
        6,
        7,
        8,
        9,
        10,
        11,
        15,
        16,
        17,
        18,
        19,
        20,
        24,
        25,
        26,
        30,
        31,
        32,
        39,
        40,
        41,
        48,
        49,
        50,
        54,
        55,
        56,
        60,
        61,
        62,
        63,
        64,
        65,
        69,
        70,
        71,
        72,
        73,
        74,
        78,
        79,
        80
    )

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
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val numberView = inflator.inflate(R.layout.sudoku_item, null)
        numberView.sudoku_number.setText(number.toString())
        if (position in colourList) {
            numberView.sudoku_number.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
            numberView.sudoku_number.setTextColor(Color.WHITE)
        }
        return numberView
    }
}