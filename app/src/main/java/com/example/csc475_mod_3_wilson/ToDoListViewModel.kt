package com.example.csc475_mod_3_wilson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.csc475_mod_3_wilson.data.ToDo
import com.example.csc475_mod_3_wilson.data.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ToDoListViewModel (
    private val toDoRepository: ToDoRepository = Graph.toDoRepository
): ViewModel() {

    var toDoTaskState by mutableStateOf("")
    var toDoNotesState by mutableStateOf("")

    fun onTaskChanged(task:String) {
        toDoTaskState = task
    }

    fun onNotesChanged(notes:String) {
        toDoNotesState = notes
    }

    lateinit var getFullToDoList : Flow<List<ToDo>>
    lateinit var getCompletedToDoList: Flow<List<ToDo>>

    init {
        viewModelScope.launch {
            getFullToDoList = toDoRepository.getFullToDoList()
            getCompletedToDoList = toDoRepository.getCompletedToDoList()
        }
    }
    fun addToDoItem(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.addToDoItem(toDo)
        }
    }

    fun getToDoItem(toDoId: Int) : Flow<ToDo> {
        return toDoRepository.getToDoItem(toDoId)
    }

    fun updateToDoItem(toDo: ToDo) {
        viewModelScope.launch (Dispatchers.IO) {
            toDoRepository.updateToDoItem(toDo)
        }
    }

    fun deleteToDoItem(toDo: ToDo) {
        viewModelScope.launch(Dispatchers.IO){
            toDoRepository.deleteToDoItem(toDo)
            getFullToDoList = toDoRepository.getFullToDoList()
        }

    }
    }


