package com.ailearn.note.room

import android.annotation.SuppressLint
import android.arch.persistence.room.Room.databaseBuilder
import android.content.Context

/**
 * Created by fulai on 2018/6/17.
 */
class DbHelper private constructor(context: Context) {
    private val noteDB: String = "noteDB"
    private val mContext: Context = context
    var noteDatabase: NoteDatabase

    init {
        noteDatabase = databaseBuilder(mContext, NoteDatabase::class.java, noteDB).build()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: DbHelper? = null
        fun getInstance(context: Context): DbHelper {
            if (instance == null) {
                synchronized(DbHelper::class) {
                    if (instance == null) {
                        instance = DbHelper(context)
                    }
                }
            }
            return instance!!
        }
    }


    fun getNoteDao(): NoteDao {
        return noteDatabase.noteDao()
    }
}