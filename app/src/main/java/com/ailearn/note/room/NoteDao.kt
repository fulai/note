package com.ailearn.note.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by fulai on 2018/6/17.
 */
@Dao
interface NoteDao {
    @Query("select * from note")
    fun getAllNotes(): List<NoteEntity>

    @Query("select * from note where id=:id")
    fun getNoteById(id: String): NoteEntity

    @Query("delete from note where id=:id")
    fun deleteNoteById(id: String)

    @Insert
    fun insert(note: NoteEntity)
}