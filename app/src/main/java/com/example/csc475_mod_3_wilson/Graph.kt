package com.example.csc475_mod_3_wilson

import android.content.Context
import androidx.room.Room
import com.example.csc475_mod_3_wilson.data.ToDoDatabase
import com.example.csc475_mod_3_wilson.data.ToDoRepository

object Graph {

    lateinit var database: ToDoDatabase

    val toDoRepository by lazy {
        ToDoRepository(toDoDao = database.toDoDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context = context, ToDoDatabase::class.java,
            name = "todolist.db").build()
    }

}