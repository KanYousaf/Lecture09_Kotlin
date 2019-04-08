package com.example.kanwal_laptop.lecture09_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_recycler_advance.*
import java.util.*

class RecyclerAdvance : AppCompatActivity() {
    private var wordList = ArrayList<String>()
    private var wordToDefinition = HashMap<String, String>()
    private lateinit var mCustomGREAdapter: myCustomGREAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_advance)
        readFromGREFile()
//        gre_word_list.adapter = ArrayAdapter(
//            this, android.R.layout.simple_list_item_1,
//            wordList
//        )
//
//        gre_word_list.setOnItemClickListener { parent, view, position, id ->
//            val wordSelected: String = gre_word_list.getItemAtPosition(position).toString()
//            val intent: Intent = Intent(this, DefinitionActivity::class.java)
//            intent.putExtra("word", wordSelected)
//            intent.putExtra("definition", wordToDefinition[wordSelected])
//            startActivity(intent)
//        }


        GREList.layoutManager = LinearLayoutManager(this)
        mCustomGREAdapter = myCustomGREAdapter(this, wordList)
        GREList.adapter = mCustomGREAdapter
        mCustomGREAdapter.onItemClick = {
            val definitionIntent: Intent = Intent(this, definitionsActivity::class.java)
            val wordSelected: String = wordList[it]
            definitionIntent.putExtra("word", wordSelected)
            definitionIntent.putExtra("definition", wordToDefinition[wordSelected])
            startActivity(definitionIntent)
        }
    }


    fun readFromGREFile() {
        val scanner: Scanner = Scanner(resources.openRawResource(R.raw.grewords))
        while (scanner.hasNextLine()) {
            val line: String = scanner.nextLine()
            val pieces = line.split("\t")
            if (pieces.size >= 2) {
                wordList.add(pieces[0])
                wordToDefinition.put(pieces[0], pieces[1])
            }
        }
    }


    fun speechBtnPressed(view: View) {
        val speechIntent: Intent = Intent(this, TextSpeech ::class.java )
        startActivity(speechIntent)
    }
}
