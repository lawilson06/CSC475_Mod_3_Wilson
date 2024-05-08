package com.example.csc475_mod_3_wilson.data

import androidx.core.view.WindowInsetsCompat.Type.InsetsType
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addToDoItem(toDoEntity: ToDo)

    @Query("SELECT * FROM `ToDo-table` WHERE `ToDo-completed` == 0")
    abstract fun getFullToDoList() : Flow<List<ToDo>>

    @Query("SELECT * FROM `ToDo-table` WHERE `ToDo-completed` != 0")
    abstract fun getCompletedToDoList() : Flow<List<ToDo>>

    @Query("SELECT * FROM `ToDo-table` WHERE `toDoId` = :toDoId")
    abstract fun getToDoItem(toDoId: Int) : Flow<ToDo>

    @Update
    abstract fun updateToDoItem(toDoEntity: ToDo)

    @Delete
    abstract fun deleteToDoItem(toDoEntity: ToDo)
}