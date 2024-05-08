package com.example.csc475_mod_3_wilson.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName="ToDo-table")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    var toDoId: Int = 0,
    @ColumnInfo(name="ToDo-task")
    var toDoTask: String = "",
    @ColumnInfo(name="ToDo-notes")
    var toDoNotes: String = "",
    @ColumnInfo(name="ToDo-completed")
    var toDoCompleted: Int = 0,
    @Ignore
    var toDoCompleteState : MutableState<Int> = mutableStateOf(0)
)

