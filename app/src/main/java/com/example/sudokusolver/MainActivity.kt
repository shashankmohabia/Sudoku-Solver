package com.example.sudokusolver

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.sudoku_item.view.*

class MainActivity : AppCompatActivity() {

    var adapter: SudokuViewAdapter? = null
    var numbersList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initializeNumberList()

        adapter = SudokuViewAdapter(this, numbersList)
        SudokuView.adapter = adapter


        fab.setOnClickListener { view ->
            for (i in 0..80) {
                val item = SudokuView.getChildAt(i).sudoku_number
                numbersList[i] = item.text.toString()
                //adapter!!.notifyDataSetChanged()
            }
            Log.d("Satya", numbersList.toString())
            solveSudoku(numbersList)
            Log.d("Satya", numbersList.toString())
        }

    }

    private fun initializeNumberList() {
        numbersList.clear()
        for (i in 1..81) {
            numbersList.add("-")
        }
        Log.d("Satya", numbersList.toString())
    }

    private fun solveSudoku(numbersList: ArrayList<String>) {
        val solver = SudokuSolver()
        val grid = Grid()
        updateInputGrid(grid)
        //Log.d("Bhavsar", grid.m_board[2][2].toString())
        solver.runAC(grid)
        solver.runBC(grid)

        //Log.d("Bhavsar", grid.m_board[2][2].toString())
        updateResult(grid)
    }

    private fun updateInputGrid(grid: Grid) {
        for (i in 0..8) {
            for (j in 0..8) {
                if (numbersList[(9 * i + j)] != "-")
                    grid.m_board[i][j] = numbersList[(9 * i + j)].toInt()
            }
        }
        //Log.d("Bhavsar", grid.m_board[2][2].toString())
    }

    private fun updateResult(board: Grid) {
        for (i in 0..8) {
            for (j in 0..8) {
                numbersList[(9 * i + j)] = (board.m_board!![i][j]).toString()
            }
        }
        //Log.d("Bhavsar", numbersList.toString())
        adapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_reset -> {
                initializeNumberList()
                adapter!!.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
