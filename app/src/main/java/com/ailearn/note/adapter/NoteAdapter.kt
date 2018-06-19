package com.ailearn.note.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ailearn.note.R
import com.ailearn.note.room.NoteEntity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Dengmao on 18/6/14.
 */
class NoteAdapter(private val context: Context, var notelist: List<NoteEntity>) : BaseAdapter() {
    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mNoteList: List<NoteEntity> = notelist

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewRoot: View
        var mViewHolder: ViewHolder
        val note: NoteEntity = notelist[position]
        if (convertView == null) {
            viewRoot = mInflater.inflate(R.layout.note_item, parent, false)
            mViewHolder = ViewHolder(viewRoot)
            viewRoot.tag = mViewHolder
        } else {
            viewRoot = convertView
            mViewHolder = viewRoot.tag as ViewHolder
        }
        if (note.dateTime != null) {
            mViewHolder.itemTimeTv.text = formatterDate(note.dateTime!!)
        }
        mViewHolder.itemTitleTv.text = note.title
        return viewRoot
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatterDate(long: Long): String {
        val date = Date(long)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return simpleDateFormat.format(date)
    }

    override fun getItem(position: Int): Any {
        return mNoteList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mNoteList.size
    }

    class ViewHolder(private var view: View) {
        var itemTitleTv: TextView = view.findViewById(R.id.item_title_tv)
        var itemTimeTv: TextView = view.findViewById(R.id.item_time_tv)
    }

}