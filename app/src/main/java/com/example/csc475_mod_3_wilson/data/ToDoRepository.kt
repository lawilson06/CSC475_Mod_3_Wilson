package com.example.csc475_mod_3_wilson.data

import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val toDoDao:ToDoDao) {

    fun getFullToDoList() = toDoDao.getFullToDoList()

    fun getCompletedToDoList() = toDoDao.getCompletedToDoList()

    fun getToDoItem(toDoId: Int) : Flow<ToDo> {
        return toDoDao.getToDoItem(toDoId)
    }

    suspend fun addToDoItem(toDo: ToDo) {
        toDoDao.addToDoItem(toDo)
    }

    suspend fun deleteToDoItem(toDo: ToDo) {
        toDoDao.deleteToDoItem(toDo)
    }

    suspend fun updateToDoItem(toDo: ToDo) {
        toDoDao.updateToDoItem(toDo)
    }

}