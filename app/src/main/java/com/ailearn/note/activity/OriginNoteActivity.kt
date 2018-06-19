package com.ailearn.note.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import com.ailearn.note.R
import kotlinx.android.synthetic.main.activity_origin_note.*

/**
 * 原笔迹
 */
class OriginNoteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_origin_note)
        val url = intent.getStringExtra("key_url")
        val bitmap = BitmapFactory.decodeFile(url)
//        val bitmap = decodeResource(resources, R.mipmap.ic_launcher)
        photoview.setImageBitmap(bitmap)
    }
}
