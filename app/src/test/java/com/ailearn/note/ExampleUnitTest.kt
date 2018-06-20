package com.ailearn.note

import com.ailearn.note.room.NoteEntity
import com.ailearn.note.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        System.out.print("aa")
    }

    @Test
    fun testGson() {
        var list: ArrayList<NoteEntity> = ArrayList()

        var ne = NoteEntity()
        ne.id = 1
        ne.title = "title"
        ne.content = "content"
        list.add(ne)
        list.add(ne)
        list.add(ne)
//        val toJson = JsonUtil.toJson(ne)
//        System.out.print(toJson)
//
//        val bean = JsonUtil.getBean(toJson, NoteEntity::class.java)
//        System.out.print(bean.id.toString() + " " + bean.title + " " + bean.content)

        val toJson1 = JsonUtil.toJson(list)
        System.out.println(toJson1)

//        val list1 = getList(toJson1)

        val gson = Gson()
        val type = object : TypeToken<List<com.ailearn.note.room.NoteEntity>>() {
        }.type
        val list1 = gson.fromJson<List<com.ailearn.note.room.NoteEntity>>(toJson1, type)
        for (v in list1) {
            System.out.print(v.id)
            System.out.print(v.title)
            System.out.println(v.content)
        }
        System.out.print(list1)
    }

    private fun <NoteEntity> getList(json: String): List<com.ailearn.note.room.NoteEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<com.ailearn.note.room.NoteEntity>>() {
        }.type
        return gson.fromJson<List<com.ailearn.note.room.NoteEntity>>(json, type)
    }

    @Test
    fun test_coroutines() {
        // 在Common线程池启动协程
        launch(CommonPool) {
            delay(2000L)    // 1
            println("Hello")
        }
        println("World")
        Thread.sleep(3000L) // 2
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
}
