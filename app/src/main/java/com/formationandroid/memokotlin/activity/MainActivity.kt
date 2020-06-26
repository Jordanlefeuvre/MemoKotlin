package com.formationandroid.memokotlin.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formationandroid.memokotlin.MainViewModel.MainViewModel
import com.formationandroid.memokotlin.bdd.MemoDTO
import com.formationandroid.memokotlin.memo.ItemTouchHelperCallback
import com.formationandroid.memokotlin.memo.MemosAdapter
import com.formationandroid.memokotlin.R
import com.formationandroid.memokotlin.repository.MainRepository
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var memosAdapter: MemosAdapter? = null
    private var listMemoDTO: MutableList<MemoDTO>? = ArrayList()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var recyclerView: RecyclerView = findViewById(R.id.liste_memos)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.init(MainRepository())
        listMemoDTO = mainViewModel.getLiveDataMemo().toMutableList()
        memosAdapter = MemosAdapter(listMemoDTO!!, this)

        liste_memos.setHasFixedSize(true)
        liste_memos.layoutManager = LinearLayoutManager(this)
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(memosAdapter!!))
        itemTouchHelper.attachToRecyclerView(liste_memos)
        liste_memos.adapter = memosAdapter
    }

    fun addMemo(v: View) {
        if (editTextMemo.text.toString().isNotEmpty()) {
            val memo = MemoDTO(editTextMemo!!.text.toString())
            listMemoDTO?.add(memo)
            mainViewModel.insertMemo(memo)
            liste_memos.adapter?.notifyDataSetChanged()
            editTextMemo.text.clear()
        }
    }
}