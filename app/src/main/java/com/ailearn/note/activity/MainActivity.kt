package com.ailearn.note.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.ailearn.note.R
import com.ailearn.note.adapter.NoteAdapter
import com.ailearn.note.room.DbHelper
import com.ailearn.note.room.NoteEntity
import com.ailearn.note.util.SDCardHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.*
import java.util.*

class MainActivity : BaseActivity() {
    var noteAdapter: NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        test_coroutines()
        setNoteAdapter()
    }

    fun test_coroutines() {
        // 在Common线程池启动协程
        launch(CommonPool) {
            delay(2000L)    // 1
            println("Hello")
        }
        println("World")
//        Thread.sleep(3000L) // 2
        // 在主线程中启动协程
        runBlocking<Unit> {
            println("T0")
            launch(CommonPool) {
                println("T1")
                delay(3000L)
                println("T2 Hello")
            }
            println("T3 World")
            delay(5000L)
            println("T4")
        }
    }

    private fun setNoteAdapter() {
        launch(CommonPool) {
            getData()
        }
    }

    private suspend fun getData(): List<NoteEntity> {
        val noteDao = DbHelper.getInstance(this).getNoteDao()
        val allNotes = noteDao.getAllNotes()
        noteAdapter = NoteAdapter(this, allNotes)
        content_list.adapter = noteAdapter
//        content_list.onItemClickListener = object : AdapterView.OnItemClickListener {
//            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//            }
//        }

        content_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val noteEntity = allNotes[position]
            var intent = Intent(this@MainActivity, OriginNoteActivity::class.java)
            intent.putExtra("key_url", noteEntity.notePicUrl)
            startActivity(intent)
        }
        return allNotes
    }


    override fun operate() {
        var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        SDCardHelper.saveBitmapToSDCardPrivateCacheDir(bitmap, "images", this@MainActivity)
        val sdCardPrivateCacheDir = SDCardHelper.getSDCardPrivateCacheDir(this@MainActivity) + "/images"
        launch(CommonPool) {
            val noteDao = DbHelper.getInstance(this@MainActivity).getNoteDao()
            var ne = NoteEntity()
            val date = Date()
            ne.title = "标题3"
            ne.content = "内容3"
            ne.dateTime = date.time
            ne.notePicUrl = sdCardPrivateCacheDir
            noteDao.insert(ne)
        }
//        startActivity(Intent(this@MainActivity, NewNoteActivity::class.java))
    }
}
