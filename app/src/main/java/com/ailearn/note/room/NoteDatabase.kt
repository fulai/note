package com.ailearn.note.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by fulai on 2018/6/17.
 */
@Database(entities = arrayOf(NoteEntity::class), version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}