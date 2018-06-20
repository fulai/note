package com.ailearn.note.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by fulai on 2018/6/17.
 */
@Entity(tableName = "note")
class NoteEntity : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
    @ColumnInfo(name = "date_time")
    var dateTime: Long? = null
    @ColumnInfo(name = "title")
    var title: String? = null
    @ColumnInfo(name = "content")
    var content: String? = null
    @ColumnInfo(name = "note_pic_url")
    var notePicUrl: String? = null

}