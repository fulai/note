package com.ailearn.note.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ailearn.note.R
import kotlinx.android.synthetic.main.comm_title_view.*

open class BaseActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.backbar_img -> {
                finish()
            }
            R.id.operatebar_tv -> {
                operate()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        backbar_img.setOnClickListener(this)
        operatebar_tv.setOnClickListener(this)
    }

    /**
     * 操作
     */
    open fun operate() {

    }

}
